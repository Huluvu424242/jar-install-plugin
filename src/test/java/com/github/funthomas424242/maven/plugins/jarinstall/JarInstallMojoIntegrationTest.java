// package com.github.funthomas424242.maven.plugins.jarinstall;
//
// import java.io.File;
//
// import org.apache.commons.io.FileUtils;
// import org.apache.maven.plugin.testing.AbstractMojoTestCase;
//
// public class JarInstallMojoIntegrationTest extends AbstractMojoTestCase {
//
// private final String LOCAL_REPO = "target/local-repo/";
//
// @Override
// public void setUp() throws Exception {
// super.setUp();
//
// FileUtils.deleteDirectory(new File(getBasedir() + "/" + LOCAL_REPO));
//
// // LegacySupport legacySupport = lookup( LegacySupport.class );
// // RepositorySystemSession repositorySession = new
// // DefaultRepositorySystemSession();
// // MavenExecutionRequest executionRequest = new
// // DefaultMavenExecutionRequest();
// // legacySupport.setSession( new MavenSession( getContainer(),
// // repositorySession, executionRequest, null ) );
// }
//
// public void testSomething() throws Exception {
//
// final File testPom = new File(getBasedir(),
// "target/test-classes/unit/flat-project/pom.xml");
//
// final JarInstallMojo mojo = (JarInstallMojo) lookupMojo("jarinstall",
// testPom);
//
// // setVariableValueToObject(mojo, "session", createMavenSession());
//
// assertNotNull(mojo);
//
// }
//
// // private MavenSession createMavenSession() {
// // final MavenSession session = Mockito.mock(MavenSession.class);
// // final DefaultRepositorySystemSession repositorySession = new
// // DefaultRepositorySystemSession();
// // repositorySession.setLocalRepositoryManager(
// // new LocalRepositoryManager(new File(LOCAL_REPO)));
// // final ProjectBuildingRequest buildingRequest = new
// // DefaultProjectBuildingRequest();
// // buildingRequest.setRepositorySession(repositorySession);
// //
// Mockito.when(session.getProjectBuildingRequest()).thenReturn(buildingRequest);
// // return session;
// // }
//
// }