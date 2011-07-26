import sbt._

class TicTacToe(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"
    val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
}