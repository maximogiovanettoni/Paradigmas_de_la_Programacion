ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

mainClass in Compile := Some("junqtional.Junqtional")

// Specify the main class for the assembly
mainClass in assembly := Some("junqtional.Junqtional") // Replace with your fully qualified main class

lazy val root = (project in file("."))
  .settings(
    name := "tp2",
    idePackagePrefix := Some("fiuba.paradigmas.tp2")
  )
