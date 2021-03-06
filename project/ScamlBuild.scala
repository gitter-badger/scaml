import sbt.Keys._
import sbt._

object ScamlBuild extends Build {
  val web = TaskKey[File]("web", "Creates api doc and tests'")

  private var testOut = "console"
  val target = new File("target/web/")
  val webSource = new File("web/")

  val webTask =
    web := {
      val log = streams.value.log


      testOut = "html"
      (test in Test).value
      val tests = new File("target/specs2-reports")
      testOut = "console"

      val docs = (doc in Compile).value

      IO.copyDirectory(webSource, target)
      IO.copyDirectory(docs, target / "api")
      IO.copyDirectory(tests, target / "tests")

      log.success("Generated web page placed at " + target)

      target
    }

  lazy val root = Project(id = "main", base = file(".")) settings(
    webTask,
    testOptions in Test := Tests.Argument(testOut) :: Nil
    )

}
