# jar-install-plugin (for maven)

[![Build Status](https://travis-ci.org/FunThomas424242/jar-install-plugin.svg?branch=master)](https://travis-ci.org/FunThomas424242/jar-install-plugin)
[ ![Download](https://api.bintray.com/packages/funthomas424242/funthomas424242-maven-plugins/jar-install-plugin/images/download.svg) ](https://bintray.com/funthomas424242/funthomas424242-maven-plugins/jar-install-plugin/_latestVersion)

You can use the jar-install-plugin in maven3 to download jar's by a given url 
and add them to the local maven repository and the maven project dependencies.

Das Plugin lädt für einen Maven Build benötigte JAR Files aus dem
Internet und installiert diese im lokalen Maven
Repository.
Die
herunter geladenen Dateien werden zusätzlich entsprechend ihres
Scopes
in die Liste der Abhängigkeiten des Maven Projektes aufgenommen.

Nützlich um JAR's von Drittanbietern in den Maven Build Prozess zu
integrieren, falls diese weder in einem zentralen
Repository
verfügbar sind oder einfach auch keine Artifakte eines Maven Builds
darstellen und ihnen somit die Metainformationen fehlen.

## Nutzung / Beispiel

Eine mögliche pom.xml folgt. Im pom wird gui4j aus dem Internet heruntergeladen und ins lokale repository installiert.



<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.github.funthomas424242</groupId>
	<artifactId>flat-project</artifactId>
	<packaging>jar</packaging>
	<version>0.0.3-SNAPSHOT</version>
	<name>Test JAR Install Plugin</name>
	<inceptionYear>2016</inceptionYear>

	<url>https://github.com/FunThomas424242/jar-install-plugin/</url>

	<dependencies>
		<!-- <dependency>
			<groupId>gui4j</groupId>
			<artifactId>gui4j</artifactId>
			<version>1.2.3</version>
			<scope>compile</scope>
		</dependency> -->
	</dependencies>

	<build>
		<defaultGoal>install</defaultGoal>
		<plugins>
			<plugin>
				<groupId>com.github.funthomas424242</groupId>
				<artifactId>jar-install-plugin</artifactId>
				<version>0.0.3-SNAPSHOT</version>
				<extensions>true</extensions>
				<executions>
					<execution>
						<id>downloadjars</id>
						<phase>validate</phase>
						<goals>
							<goal>install</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<!-- Specify the MyMojo parameter -->
					<downloads>
						<downloadArtifact>
							<url>http://sourceforge.net/projects/gui4j/files/gui4j/1.2.3/gui4j-1.2.3.jar/download</url>
							<groupId>gui4j</groupId>
							<artifactId>gui4j</artifactId>
							<version>1.2.3</version>
							<scope>compile</scope>
						</downloadArtifact>
					</downloads>
				</configuration>

			</plugin>
		</plugins>
	</build>

</project>



## Nützliche Quellen im Zwischennetz (Neuland in de)

* https://maven.apache.org/plugin-developers/plugin-testing.html
* https://github.com/apache/maven-plugins/blob/trunk/maven-install-plugin/src/test/java/org/apache/maven/plugin/install/InstallFileMojoTest.java


## Project Sites

**Maven Repositories**

 - snapshot repository: https://repository-funthomas424242.forge.cloudbees.com/snapshot/
 - release repository: https://repository-funthomas424242.forge.cloudbees.com/release/

**Project on GitHub:** https://github.com/funthomas424242/jar-install-plugin/

**The old sources** are hosted on sourceforge: 
http://sf-mvn-plugins.svn.sourceforge.net/viewvc/sf-mvn-plugins/ (svn repo)


## Powered by


[![Maven](http://maven.apache.org/images/logos/maven-feather.png)](http://maven.apache.org)


