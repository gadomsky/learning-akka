
name := "akkadb-client"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"   % "2.5.1",
  "com.typesafe.akka" %% "akka-remote"   % "2.5.1",
  "com.gadomski.akkadb" %% "akka-demy-db" % "1.0-SNAPSHOT",
  "org.scalatest"     %% "scalatest"    % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.1" % "test"
)
