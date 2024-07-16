name := "scala-playground"

version := "0.1"

scalaVersion := "3.3.3"


libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.12.5",
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "org.scalatest" %% "scalatest-funspec" % "3.2.19" % Test
)

