/**
 *
 */
package com.github.funthomas424242.maven.plugins.jarinstall;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.installer.ArtifactInstallationException;
import org.apache.maven.artifact.installer.ArtifactInstaller;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

/**
 * @author SchubertT006
 * @goal install
 * @phase validate
 * @requiersProject
 */
public class JarInstallMojo extends AbstractMojo {

	final protected Log logger = getLog();

	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * Used for attaching the artifact in the project.
	 *
	 * @component
	 *
	 * @required
	 * @readonly
	 */
	protected MavenProjectHelper projectHelper;

	/**
	 * @parameter property="localRepository"
	 * @required
	 * @readonly
	 */
	protected ArtifactRepository localRepository;

	/**
	 * @component
	 * @required
	 * @readonly
	 */
	protected ArtifactFactory artifactFactory;

	/**
	 * @component
	 * @required
	 * @readonly
	 */
	protected ArtifactInstaller installer;

	/**
	 * @parameter
	 * @required
	 *
	 */
	protected List<DownloadArtifact> downloads;

	protected void printInfo(final String message) {
		logger.info(message);
	}

	protected void printError(final String message) {
		logger.error(message);
	}

	protected void printError(final Exception e) {
		logger.error(e);
	}

	protected void printDebug(final String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	protected void printDebug(final Exception e) {
		if (logger.isDebugEnabled()) {
			logger.debug(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// <configuration>
		// <downloads>
		// <downloadArtifact>
		// <groupId>
		// <artifactId>
		// <version>
		// <url>
		// <pomURL>
		// </downloadArtifact>
		// </downloads>
		// </configuration>
		//

		printInfo("START");
		final ListIterator<DownloadArtifact> it = downloads.listIterator();
		while (it.hasNext()) {
			final DownloadArtifact downloadArtifact = it.next();
			// downloadArtifact allways not null

			final String groupId = downloadArtifact.getGroupId();
			// "com.simontuffs.onejar";

			final String artifactId = downloadArtifact.getArtifactId();
			// "one-jar-boot";

			final String version = downloadArtifact.getVersion();
			// "0.96";
			final String scope = downloadArtifact.getScope();
			// null;
			String type = downloadArtifact.getType();
			// "jar";

			final String url = downloadArtifact.getUrl();
			// "http://sourceforge.net/projects/one-jar/files/one-jar/one-jar-0.96/one-jar-boot-0.96.jar/download";

			// validation part
			if (groupId == null) {
				throw new MojoExecutionException(
						"You must configure a groupId");
			}
			if (artifactId == null) {
				throw new MojoExecutionException(
						"You must configure a artifactId");
			}

			if (version == null) {
				throw new MojoExecutionException(
						"You must configure a version of artifact.");
			}

			if (url == null) {
				throw new MojoExecutionException(
						"You must configure a download URL for artifact");
			}

			printInfo("Check artifact " + downloadArtifact + " for download.");

			// default initialization
			if (type == null) {
				type = "jar";
			}

			final Artifact artifact = artifactFactory.createArtifact(groupId,
					artifactId, version, scope, type);

			addProjectDependency(artifact);

			// check for add to local repository
			final String artifactPath = this.localRepository.getBasedir() + "/"
					+ this.localRepository.pathOf(artifact);
			printDebug("Path:" + artifactPath);

			final File artifactFile = new File(artifactPath);
			if (artifactFile.exists()) {
				printInfo("Artifact " + artifact.getArtifactId()
						+ " already downloaded.");
				// !!! next artifact in for
				continue;
			} else {
				printInfo("DownloadArtifact Artifact "
						+ artifact.getArtifactId());
			}

			final String tmpDir = this.localRepository.getBasedir();
			printDebug("Creating temp file at: " + tmpDir);
			final String tmpFileName = tmpDir + File.separator + groupId + "."
					+ artifactId + "-" + version + ".jar.tmp";
			final File tmpFile = new File(tmpFileName);
			if (tmpFile.exists()) {
				try {
					tmpFile.createNewFile();
				} catch (final IOException e) {
					printError(e);
				}
			}

			copyURLToTmpFile(url, tmpFile);

			try {
				installer.install(tmpFile, artifact, localRepository);
			} catch (final ArtifactInstallationException e) {
				printError(e);
			}

			// delete the local tmp file.
			if (!tmpFile.delete()) {
				printInfo("Could not delete file " + tmpFile.getAbsolutePath());
			}

		} /* next downloadArtifact */

	}

	protected void addProjectDependency(final Artifact artifact) {
		// add as dependency with given scope (anytime)
		final Set<Artifact> dependencyArtifacts = this.project
				.getDependencyArtifacts();
		// TODO Bad fix for not supported Operation add in dependencyArtifacts
		final Set<Artifact> newArtifacts = new HashSet<Artifact>();
		newArtifacts.addAll(dependencyArtifacts);
		newArtifacts.add(artifact);
		this.project.setDependencyArtifacts(newArtifacts);
	}

	/**
	 * Copy the file content from URL to local tmp File.
	 *
	 * @param url
	 * @param tmpFile
	 * @return
	 */
	protected void copyURLToTmpFile(final String url, final File tmpFile) {
		FileOutputStream outStream = null;
		try {
			final URL archivURL = new URL(url);
			final URLConnection con = archivURL.openConnection();
			final InputStream inStream = con.getInputStream();
			outStream = new FileOutputStream(tmpFile);
			try {
				int b = inStream.read();
				while (b != -1) {
					outStream.write(b);
					b = inStream.read();
				}
			} finally {
				IOUtils.closeQuietly(inStream);
			}
		} catch (final MalformedURLException e) {
			printError(e);
		} catch (final IOException e) {
			printError(e);
		} finally {
			IOUtils.closeQuietly(outStream);
		}
	}
}
