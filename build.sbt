name := "Connect 4 Scala"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)

libraryDependencies ++=Seq(
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test",
  "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
  "com.google.inject" % "guice" % "5.0.1",
  "net.codingwell" %% "scala-guice" % "5.0.2",
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0",
  "com.typesafe.play" %% "play-json" % "2.9.2"
)
