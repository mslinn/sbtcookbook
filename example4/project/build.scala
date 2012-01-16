// Example 1 from Josh Suereth's SBT Cookbook
// @see https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1

import sbt._
import sbt.Keys._

object MyBuild extends Build {
  val awesomez = Project("scala-awesomez", file(".")) settings(
    name:= "scala-awesomez",
    organization := "com.jsuereth"
  ) dependsOn(arm)
  lazy val arm = RootProject(uri("git://github.com/jsuereth/scala-arm.git"))
}

