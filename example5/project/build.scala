// Example 5 from Josh Suereth's SBT Cookbook
// @see https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1

import sbt._
import sbt.Keys._

object MyBuild extends Build {
  val distConfig = config("dist")

  def makeZipDist(mappings: Seq[(File, String)], dir: File): File = {
    if (!dir.exists)
      dir.mkdirs()
    val zip = dir / "my-project-release-latest.zip"
    IO.zip(mappings, zip)
    zip
  }

  inConfig(distConfig)(Seq(
  /* [error] No implicit for Append.Value[Seq[(java.io.File, String)], sbt.Project.Initialize[sbt.Task[(java.io.File, java.lang.String)]]] found,
  [error]   so sbt.Project.Initialize[sbt.Task[(java.io.File, java.lang.String)]] cannot be appended to Seq[(java.io.File, String)] */
    mappings := Seq.empty,
    mappings += (packageBin in Compile) map (_ -> "main.jar"),
    mappings += (packageDoc in compile) map (_ -> "docs.jar"),
    packageBin <<= (mappings, baseDirectory) map makeZipDist
  ))
}

