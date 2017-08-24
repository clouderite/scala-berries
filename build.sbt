lazy val commonSettings = Seq(
  scalaVersion := "2.12.1",
  crossScalaVersions := Seq("2.11.8", "2.12.1"),

  organization := "io.clouderite.commons",
  name := "scala-berries",
  version := "1.0.2",

  libraryDependencies ++= {
    val akkaV = "2.5.0"
    val akkaHttpV = "10.0.5"

    Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.typesafe.akka" %% "akka-actor" % akkaV,
      "com.typesafe.akka" %% "akka-cluster" % akkaV,
      "com.typesafe.akka" %% "akka-cluster-tools" % akkaV,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
      "com.typesafe.akka" %% "akka-testkit" % akkaV,
      "org.scalaz" %% "scalaz-core" % "7.2.14",

      "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test",
      "org.mockito" % "mockito-core" % "1.10.19" % "test"
    )
  },

  fork := true,
  scalacOptions ++= Seq("-Xmax-classfile-name", "110")
)

lazy val publishSettings = Seq(
  ivyLoggingLevel := UpdateLogging.Full,
  publishArtifact := true,
  publishArtifact in Test := false,
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false },

  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),

  publishTo := Some(
    if (isSnapshot.value)
      "Sonatype Releases Nexus" at "https://maven.clouderite.io/nexus/content/repositories/snapshots/"
    else
      "Sonatype Releases Nexus" at "https://maven.clouderite.io/nexus/content/repositories/releases/"
  )
)

lazy val app = project
  .in(file("."))
  .settings(commonSettings)
  .settings(publishSettings)

