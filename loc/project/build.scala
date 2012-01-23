import sbt._
import Keys._

/*
In SBT type:

     show loc

*/
object Loc extends Build {
  val hwsettings = Defaults.defaultSettings ++ Seq(
    organization := "Micronautics Research",
    name         := "loc",
    version      := "0.1",
    scalaVersion := "2.9.1"
  )

  val loc = TaskKey[Unit]("loc", "Displays the number of lines of code in the project")

/** Doesn't ignore comments. */
def line_count(file: File): Long =  IO.readLines(file).length   

    def sources_line_count(files: Seq[File]): Long = 
      if (files.isEmpty) 
        0
      else  
        files.par.map(line_count).reduce(_+_)

     val locTask = loc <<= (sources in Compile) map sources_line_count

  //val locTask = loc := {
    // need to learn how to read a file so this next line can print the number of lines
//    println((sources in Compile) map (s => s.last.getAbsolutePath).readFileSomehow.lines.map(_.length).sum)
	//set TaskKey[Unit]("k") <<= (sources in Compile) map (s => println(s.last.getName))
    //println("This project has " + sourceFiles.length + " files containing source code")
  //}

  lazy val project = Project("project", file ("."), settings = hwsettings ++ Seq(locTask))
}

