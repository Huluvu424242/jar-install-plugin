//package com.github.funthomas424242.maven.plugins.jarinstall;
//
//import org.apache.maven.plugin.testing.AbstractMojoTestCase;
//import org.apache.maven.plugin.testing.MojoRule;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.io.File;
//
//public class JarInstallMojoTest extends AbstractMojoTestCase {
//
//    @Rule
//    public MojoRule rule = new MojoRule() {
//        @Override
//        protected void before() throws Throwable {
//        }
//
//        @Override
//        protected void after() {
//        }
//    };
//
//    @Test
//    public void testSomething() throws Exception {
//        final File pom = getTestFile(
//                "src/test/resources/unit/flat-project/pom.xml");
//        assertNull(pom);
//        assertTrue(pom.exists());
//
//        final JarInstallMojo myMojo = (JarInstallMojo) lookupMojo("install",
//                pom);
//        assertNotNull(myMojo);
//        myMojo.execute();
//    }
//}