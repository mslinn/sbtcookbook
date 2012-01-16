// Example 3 from Josh Suereth's SBT Cookbook
// @see https://docs.google.com/present/view?id=dfqn4jb_115x89dq2dg&pli=1

import sbt._
import sbt.Keys._

object MyBuild extends Build {
  val FunTest  = config("fun") extend(Test)
  val specs    = "org.scala-tools.testing" %% "specs" % "1.9" % "fun"
  val awesomez = Project("scala-awesomez", file(".")) settings(
    name:= "scala-awesomez3",
    organization := "com.jsuereth"
    inConfig(FunTest)(Defaults.testSettings):_*)
  ) settings (
    libraryDependencies += specs,
    sourceDirectory in FunTest <<= sourceDirectory apply (_ / "functional")
  )
}

