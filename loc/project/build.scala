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

/* Thanks Josh! Will get back to this in a couple of days
def line_count(file: File): Long =  IO.readLines(file).length   // NOTE: Horribly inefficient and doesn't ignore comments.

    def sources_line_count(file: Seq[File]): Long = 
      if(files.empty) 0
      else  file.par.map(line_count).reduce(_+_)

     val locTask = lock <<= (sources in Compile) map sources_line_count


then in SBT type:

     show loc*/

  val locTask = loc := {
    // need to learn how to read a file so this next line can print the number of lines
    println((sources in Compile) map (s => s.last.getAbsolutePath).readFileSomehow.lines.map(_.length).sum)
	//set TaskKey[Unit]("k") <<= (sources in Compile) map (s => println(s.last.getName))
    //println("This project has " + sourceFiles.length + " files containing source code")
  }

  lazy val project = Project("project", file ("."), settings = hwsettings ++ Seq(locTask))
}

