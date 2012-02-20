name := "Spring3-Scala"

organization := "org.nate"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq("org.springframework" % "spring-jdbc" % Versions.spring,
							"org.springframework" % "spring-webmvc" % Versions.spring,
							"org.slf4j" % "slf4j-api" % Versions.slf4j,
							"org.scalatest" %% "scalatest" % Versions.scalatest % "test",
							"org.mockito" % "mockito-all" % Versions.mockito % "test",
							"commons-dbcp" % "commons-dbcp" % Versions.dbcp,
							"com.h2database" % "h2" % Versions.h2,
							"javax.servlet" % "jstl" % Versions.jstl,
							"taglibs" % "standard" % Versions.taglibs)
						
publishMavenStyle := true

publishArtifact in Test := false

publishTo := Some(Resolver.file("file",  new File("c:/maven-local-repo")))