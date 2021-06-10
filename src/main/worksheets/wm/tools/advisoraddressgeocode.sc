import com.wealthminder.common.data.dao.AddressGeoCodeDAO
import com.wealthminder.common.data.dto.AddressGeoCode
import dispatch.Defaults._
import dispatch._
import org.json4s.DefaultFormats
import org.json4s.JsonAST._
import org.json4s.native.JsonMethods._
import scala.concurrent.Future
import scala.reflect.io.Path
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.{StaticQuery => Q}
import scala.util.{Failure, Success, Try}

case class Location(lat:Double, lng:Double)
case class Geometry(location:Location)
case class GeoCode(geometry:Geometry)
case class GoogleGeoCodeResult(results: List[GeoCode], status:String)

case class CityResults(json:String, address:AddressGeoCode)

//val googleKey:String = "AIzaSyAL-XvfFYJPbyQ1SaTd1sgUYXlu91cBT7Q"
val googleKey: String = "AIzaSyASNyvEXS8X8nUALivf-70-XfJQSvOWPJQ"

def createUpdateStmt(address: AddressGeoCode):String =
  s"update address_geo_code set latitude = ${address.latitude} , longitude = ${address.longitude}  where address='${address.address.replaceAll("'", "''")}';"

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")

implicit val formats = DefaultFormats

def getGoogleGeoCode(size:Option[Int], sleepTimeMillis:Int) = {

    val addresses:List[AddressGeoCode] = db withSession { implicit session: Session =>
      AddressGeoCodeDAO.getAddresses(size)
    }
  Future.sequence (
    addresses.map { add =>
    Thread.sleep(sleepTimeMillis)
    Try {
      val address = s"${java.net.URLEncoder.encode(add.address, "UTF-8")}"
      val googleUrl = s"https://maps.googleapis.com/maps/api/geocode/json?address=${address}&region=us&key=${googleKey}"
      val svc = url(googleUrl)
//      println(googleUrl)
      val googleRes: Future[JValue] = Http(svc.GET OK as.json4s.Json)

      googleRes.map { j =>
        val geoCodeResult:GoogleGeoCodeResult = (j.extract[GoogleGeoCodeResult])
//        println(s"${add.address}\t=>\t${geoCodeResult.status}")
        geoCodeResult.status match {
          case "OK" => {
            val res:List[(Double,Double)] = geoCodeResult.results.map(g => (g.geometry.location.lat, g.geometry.location.lng))
            val (newLatitude: Double, newLongitude: Double) = res.headOption match {
              case Some(l) => (l._1, l._2)
              case _ => (null, null)
            }
            val addressModified = add.copy(latitude = newLatitude, longitude = newLongitude)
            Some(CityResults(compact(render(j)), addressModified))
          }
          case _ => {
            println(s"${add.address}\t=>\t${geoCodeResult.status} \t - ${googleUrl} " )
            None
          }
        }
      }
    } match {
      case Success(result) => result
      case Failure(f) => {
        println (f.getMessage)
        Future { None }
      }
    }
  }).map{ cityList =>

    println("Completed with google API calls...")
    val filteredCityList:List[CityResults] =
      cityList
        .flatten

    println("Generated filtered list of cities...")
    val updateQuery:String =
      filteredCityList
        .map(cr => createUpdateStmt(cr.address))
        .mkString("\n")

    println("Update SQL created...")

    val sqlPath = Path("/datadrive/address_geo_code_update.sql")
    val sqlFile = sqlPath.createFile(false)
    sqlFile.appendAll(updateQuery)

    println("Update SQL written to file...")
    val existingJsonLines:List[String] = Try {
      scala.io.Source.fromFile("/datadrive/address_geo_code_json.txt").getLines().toList
    } match {
      case Success(lines) => lines
      case _ => List()
    }

    val existingJsonText:String = existingJsonLines.size match {
      case l if l >= 3 => existingJsonLines.tail.dropRight(1).mkString("\n") + ","
      case _ => ""
    }
    println("completed reading existing json...")

    val jsonText:String = existingJsonText + filteredCityList.map(_.json).mkString(",\n")
    val jsonFilePath = Path("/datadrive/address_geo_code_json.txt")
    val jsonFile = jsonFilePath.createFile(false)
    if(!jsonText.isEmpty) jsonFile.writeAll("[\n" + jsonText + "\n]")

    println("completed writing json to file...")
    Unit
  }
}

getGoogleGeoCode(Some(10), 20)

