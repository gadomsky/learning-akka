name := "akka-demy-db"
organization := "com.gadomski.akkadb"
version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"   % "2.5.1",
  "com.typesafe.akka" %% "akka-remote"   % "2.5.1",
  "org.scalatest"     %% "scalatest"    % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.1" % "test"
)

mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) =>
  Seq("application.conf").contains(name)
}}

