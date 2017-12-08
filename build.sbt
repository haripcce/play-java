name := """play-java-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"
routesGenerator := InjectedRoutesGenerator
lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.12"
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)
libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies ++= Seq(evolutions, jdbc)