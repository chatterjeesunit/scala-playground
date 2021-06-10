import java.io.{File, BufferedWriter, FileWriter}

case class AdCity(city:String, state:String, country:String, zip:String, latitude:String, longitude:String)

//Convert Array to AdCity Object
def getAdCity(line:Array[String]):AdCity = {
  AdCity(
    line(1).replaceAll("'", "''"),
    line(10),
    line(8),
    null,
    line(4),
    line(5))
}

def getInsertValString(ac:AdCity):String =
  s" ( '${ac.city}' , '${ac.state}' , '${ac.country}' , '${ac.zip}' , '${ac.latitude}' , '${ac.longitude}' )"


//Read the input file
def readLines(filePath:String):List[String] = {
  scala.io.Source.fromFile(filePath, "UTF8")
  .getLines()                             //Reading Lines from input file
  .map(_.stripLineEnd.split("\t", -1))    //Breaking each line into Array of Strings, based on tab delimiter
  .map(l => getAdCity(l))                 //Converting each line of String Array, into AdCity Object
  .toList                                 //Converting it to List[AdCity]
  .groupBy(ac => (ac.city,ac.zip))        //Grouping by City,Zip, to remove duplicates
  .map(m => getInsertValString(m._2(0)))  //For each duplicate record, pick the first AdCity record, and convert it into Insert Value String 
  .toList                                 //Convert into a list
}

//Write the SQL to File
def writeSQL(filePath:String, lines:List[String], preProcess:String, postProcess:String, groupSize:Int):Unit = {
  val outFile = new File(filePath)
  val linesList = lines grouped groupSize
  val writer = new BufferedWriter(new FileWriter(outFile))
  writer.write(preProcess)
  writer.newLine
  linesList.foreach(ll => {
    writer.write(s"INSERT INTO `ad_cities` (`city`, `state_code`, `country`, `postal_code`, `latitude`,`longitude`) ")
    writer.newLine
    writer.write("values ")
    writer.newLine
    writer.write(ll.mkString(",\n"))
    writer.write(";")
    writer.newLine
  })
  writer.write(postProcess)
  writer.newLine
  writer.flush
  writer.close
}
val inputFile = "/datadrive/WealthMinder/Work/marketplace/M4/cityData/US_postal/US.txt"
val outputFile = "/datadrive/WealthMinder/Work/marketplace/M4/cityData/US_postal/US.sql"
val preProcess:String =
  "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */; \n" +
  "/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */; \n" +
  "/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */; \n" +
  "/*!40101 SET NAMES utf8 */; \n" +
  "/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */; \n" +
  "/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */; \n" +
  "/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */; \n" +
  "LOCK TABLES `ad_cities` WRITE; \n" +
  "/*!40000 ALTER TABLE `ad_cities` DISABLE KEYS */; \n"
val postProcess:String =
  "/*!40000 ALTER TABLE `ad_cities` ENABLE KEYS */; \n" +
  "UNLOCK TABLES; \n" +
  "/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */; \n" +
  "/*!40101 SET SQL_MODE=@OLD_SQL_MODE */; \n" +
  "/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */; \n" +
  "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */; \n" +
  "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */; \n" +
  "/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */; \n"

// Size reduced from 43633 to 43632

writeSQL(outputFile, readLines(inputFile), preProcess, postProcess, 50)