// Example 2 from Josh Suereth's SBT Cookbook
// @see https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1

import sbt._
import sbt.Keys._

object MyBuild extends Build {
  val awesomez = Project("scala-awesomez", file(".")) settings(
    name:= "scala-awesomez",
    organization := "com.jsuereth"
    libraryDependencies += "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test"
  ) dependsOn(arm)
  lazy val arm = RootProject(uri("git://github.com/jsuereth/scala-arm.git"))
}
