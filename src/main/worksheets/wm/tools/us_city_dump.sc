import java.io.{File, BufferedWriter, FileWriter}

//def getString(line:Array[String]) =
// s"INSERT INTO `cities` (`geo_code_id`, `city`, `state_code`, `postal_code`, `country`, `latitude`,`longitude`) VALUES ('${line(0)}' , '${line(1)}' , '${line(10)}' , null,' ${line(8)}' , '${line(4)}' , '${line(5)}');"

def getString(line:Array[String]) =
 s"INSERT INTO `cities` (`geo_code_id`, `city`, `state_code`, `country`, `latitude`,`longitude`) \n values " +
   s"('${line(0)}' , '${line(1)}' , '${line(10)}' ,' ${line(8)}' , '${line(4)}' , '${line(5)}')"


def isStateDefined(line:Array[String]):Boolean = !("".equals(Option(line(10)).getOrElse("")))
//Code Reference for below comparisons - http://www.geonames.org/export/codes.html
def isCity(line:Array[String]):Boolean =
  "P".equals(line(6)) &&
    !"PPLQ".equals(line(7)) && !"PPLW".equals(line(7)) && !"PPLH".equals(line(7))

val inFile = scala.io.Source.fromFile("/datadrive/WealthMinder/Work/marketplace/M4/cityData/US/US.txt", "UTF8")
val lines = inFile
  .getLines()
  .map(_.stripLineEnd.split("\t", -1))
  .filter(line => (isCity(line) && isStateDefined(line)))
  .map(l => getString(l))


val linesGrouped = lines grouped 50

val outFile = new File("/datadrive/WealthMinder/Work/marketplace/M4/cityData/US/US.sql")
val writer = new BufferedWriter(new FileWriter(outFile))
lines.foreach(line => {
  writer.write(line)
  writer.newLine
})
writer.flush
writer.close

182177
2208902
182177.0 / 2208902.0

