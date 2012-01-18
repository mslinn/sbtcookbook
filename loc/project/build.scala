import sbt._
import Keys._

object HelloBuild extends Build {
  val hwsettings = Defaults.defaultSettings ++ Seq(
    organization := "Micronautics Research",
    name         := "loc",
    version      := "0.1",
    scalaVersion := "2.9.1"
  )

  val loc = TaskKey[Unit]("loc", "Displays the number of lines of code in the project")

  val locTask = loc := {
	set TaskKey[Unit]("k") <<= (sources in Compile) map (s => println(s.last.getName))
    set TaskKey[Unit]("l") <<= (sources in Compile) map (s => s.last.getAbsolutePath)
    //println("This project has " + sourceFiles.length + " files containing source code")
  }

  lazy val project = Project("project", file ("."), settings = hwsettings ++ Seq(locTask))
}

