import java.io.File

import scala.reflect.io.Path

val fileName:String = "/home/sunitc/source/wealthminder/webapiapp/src/main/resources/botlist.txt"
val lines = scala.io.Source.fromFile(fileName, "UTF-8")
lines.getLines().mkString("\n")


val dir = "/home/sunitc/source/wealthminder/webapiapp/target/test-reports"
val file = new File(dir)
val data =
  file
    .listFiles()
    .filterNot(_.isDirectory)
    .map("testOnly " + _.getName.replace(".xml", ""))
    .sorted
    .mkString("\n")


val outPath = Path("/datadrive/WealthMinder/Work/marketplace/M14/tests/sameErrors.txt")
val outFile = outPath.createFile(false)
outFile.writeAll(data)

