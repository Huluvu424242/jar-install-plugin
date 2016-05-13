package com.github.funthomas424242.maven.plugins.jarinstall;

import java.io.File;

public class JarInstallMojoIntegrationTest extends AbstractMojoTestCase {

	private final String LOCAL_REPO = "target/local-repo/";

	@Override
	public void setUp() throws Exception {
		super.setUp();

		FileUtils.deleteDirectory(new File(getBasedir() + "/" + LOCAL_REPO));

		// LegacySupport legacySupport = lookup( LegacySupport.class );
		// RepositorySystemSession repositorySession = new
		// DefaultRepositorySystemSession();
		// MavenExecutionRequest executionRequest = new
		// DefaultMavenExecutionRequest();
		// legacySupport.setSession( new MavenSession( getContainer(),
		// repositorySession, executionRequest, null ) );
	}

	public void testSomething() throws Exception {

		final File testPom = new File(getBasedir(),
				"target/test-classes/unit/flat-project/pom.xml");

		final JarInstallMojo mojo = (JarInstallMojo) lookupMojo("install",
				testPom);

		setVariableValueToObject(mojo, "session", createMavenSession());

		assertNotNull(mojo);

	}

	private MavenSession createMavenSession() {
		final MavenSession session = mock(MavenSession.class);
		final DefaultRepositorySystemSession repositorySession = new DefaultRepositorySystemSession();
		repositorySession.setLocalRepositoryManager(
				new EnhancedLocalRepositoryManager(new File(LOCAL_REPO)));
		final ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest();
		buildingRequest.setRepositorySession(repositorySession);
		when(session.getProjectBuildingRequest()).thenReturn(buildingRequest);
		return session;
	}

}