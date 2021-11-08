ThisBuild / scalaVersion := "3.1.0"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "net.stouma915"
ThisBuild / description := "水素で遊ぼう"

lazy val root = (project in file("."))
  .settings(
    name := "HydrogenMod",
    assemblyOutputPath / assembly := baseDirectory.value / "target" / "build" / s"HydrogenMod-${version.value}.jar"
  )
