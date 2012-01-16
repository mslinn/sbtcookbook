// Example 4 from Josh Suereth's SBT Cookbook
// @see https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1

import sbt._
import sbt.Keys._

object MyBuild extends Build {
  val template = """object Properties { +
    def name = "{name}"
  }"""
  def generatePropertiesFile(dir: File, name: String) = {
    val file = dir / "buildproperties.scala"
    IO.write(file, template.replaceAll("\\{name\\}", name)) // MOS corrected backslashes
    Seq(file)
  }
  /* [error] overloaded method value settings with alternatives:
  [error]   (ss: sbt.Project.Setting[_]*)sbt.Project <and>
  [error]   => Seq[sbt.Project.Setting[_]]
  [error]  cannot be applied to (Seq[sbt.Project.Setting[_]])
  [error]   val awesomez = Project("scala-awesomez", file(".")) settings(inConfig(Compile)(  */
  val awesomez = Project("scala-awesomez", file(".")) settings(inConfig(Compile)(
    sourceGenerators <+= (sourceManaged, name) map generatePropertiesFile
  ))
}

