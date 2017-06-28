name := "akkademaid"
organization := "com.gadomski.akkademaid"
version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.syncthemall" % "boilerpipe" % "1.2.2",
  "com.gadomski.akkadb" %% "akka-demy-db" % "1.0-SNAPSHOT",
  "org.scalatest"     %% "scalatest"    % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.1" % "test"
)
