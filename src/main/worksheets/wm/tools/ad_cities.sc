import com.wealthminder.marketplace.data.dto.AdCity
import java.io.{File, BufferedWriter, FileWriter}

def getInsertValString(ac:AdCity):String =
  s" (${ac.adCityId} ,  '${ac.city.replaceAll("'", "''")}' , '${ac.stateCode}' , " +
    s"'${ac.country}' , '${ac.postalCode}' , " +
    s"${ac.latitude} , ${ac.longitude}, '${ac.postalCode}' )"


//Read the input file
def readLines(filePath:String):List[String] = {
  scala.io.Source.fromFile(filePath, "UTF8")
    .getLines()                             //Reading Lines from input file
    .toList    //convert to list
    .tail     // remove the header line
    .map(_.replaceAll("\"", "")) //Remove double quotes
    .map(_.stripLineEnd) //Breaking each line into Array of Strings, based on tab delimiter
    .map(_.split(",", -1)) //Split csv into array
    .map(c =>
      AdCity(c(0).toLong, c(1), c(2), c(3), c(4), c(5).toDouble,
        c(6).toDouble, c(4))
    ) //Converting each line of String Array, into AdCity Object
    .groupBy(ac => (ac.city,ac.postalCode))        //Grouping by City,Zip, to remove duplicates
    .map(m => getInsertValString(m._2(0)))  //For each duplicate record, pick the first AdCity record, and convert it into Insert Value String
    .toList                                 //Convert into a list
}

implicit val ord = Ordering.by{a:AdCity => a.adCityId}
//Write the SQL to File
def writeSQL(filePath:String, lines:List[String], preProcess:String, postProcess:String, groupSize:Int):Unit = {
  val outFile = new File(filePath)
  val linesList = lines.sorted grouped groupSize
  val writer = new BufferedWriter(new FileWriter(outFile))
  writer.write(preProcess)
  writer.newLine
  writer.write("delete from ad_cities;")
  writer.newLine
  linesList.foreach(ll => {
    writer.write(s"INSERT INTO `ad_cities` (`ad_cities_id`, `city`, `state_code`, `country`, `postal_code`, `latitude`,`longitude`,`postal_code_override`) ")
    writer.newLine
    writer.write("values ")
    writer.newLine
    writer.write(ll.mkString(",\n"))
    writer.write(";")
    writer.newLine
  })
  writer.write(postProcess)
  writer.newLine
  writer.write("update `ad_cities` set postal_code_override = postal_code;")
  writer.newLine
  writer.write("commit;")
  writer.newLine
  writer.flush
  writer.close
}
val inputFile = "/datadrive/WealthMinder/Work/SetupRelated/databaseDumps/ad_cities_prod.csv"
val outputFile = "/datadrive/WealthMinder/Work/SetupRelated/databaseDumps/ad_cities_prod.sql"
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

//readLines(inputFile)