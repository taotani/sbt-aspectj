
import ReleaseTransformations._
lazy val root = (project in file("."))
    .enablePlugins(SbtPlugin)
    .settings(

      organization := "com.lightbend.sbt",
      name := "sbt-aspectj",

      libraryDependencies += "org.aspectj" % "aspectjtools" % "1.9.5",

      publishMavenStyle := false,

      crossSbtVersions := Vector("1.3.5"),

      bintrayOrganization := Some("sbt"),
      bintrayRepository := "sbt-plugin-releases",
      bintrayPackage := name.value,
      bintrayReleaseOnPublish := false,
      scriptedDependencies := publishLocal.value,
      scriptedLaunchOpts ++= Seq("-Xms512m", "-Xmx512m", "-Dproject.version=" + version.value),

      releaseProcess := Seq[ReleaseStep](
        checkSnapshotDependencies,
        inquireVersions,
        runClean,
        releaseStepCommandAndRemaining("^ scripted"),
        setReleaseVersion,
        commitReleaseVersion,
        tagRelease,
        releaseStepCommandAndRemaining("^ publish"),
        releaseStepTask(bintrayRelease),
        setNextVersion,
        commitNextVersion,
        pushChanges
      )
)



