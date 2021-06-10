import com.wealthminder.marketplace.data.dto.AdCity
import dispatch.Defaults._
import dispatch._
import org.json4s.DefaultFormats
import org.json4s.JsonAST._
import org.json4s.native.JsonMethods._
import scala.concurrent.Future
import scala.reflect.io.Path
import slick.driver.MySQLDriver.simple._
import slick.jdbc.{GetResult, StaticQuery => Q}
import scala.util.{Failure, Success, Try}

case class AdCityNew(city:String, state:String, postalCode:String, oldLat:Double, oldLong:Double, newLat:Double, newLong:Double)
case class Location(lat:Double, lng:Double)
case class Geometry(location:Location)
case class GeoCode(geometry:Geometry)
case class GoogleGeoCodeResult(results: List[GeoCode], status:String)
case class CityResults(json:String, city:AdCityNew)

val googleKey:String = "AIzaSyASNyvEXS8X8nUALivf-70-XfJQSvOWPJQ"

def createUpdateStmt(city: AdCityNew):String =
  s"update ad_cities set latitude = ${city.newLat} , longitude = ${city.newLong} , processed = 1 where city='${city.city.replaceAll("'", "''")}' and  state_code='${city.state}' and  postal_code='${city.postalCode}';"

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")

def getAdCities(size:Int):List[AdCity] = {
  db withSession { implicit session: Session =>
    val queryString: String = "select ad_cities_id, city, state_code, country, postal_code, latitude, longitude , " +
      s"postal_code_override from ad_cities where processed = 0 limit ${size}"
    implicit val getAdvisorResult = GetResult(r => AdCity(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
    println(queryString)
    Q.queryNA[AdCity](queryString).list
  }
}
//val results = List(
//  AdCity(34224, "Castleton-On-Hudson", "NY", "US", "12033", 42.58915800, -73.56592500, "12033"),
//  AdCity(11765, "Coeur d'Alene", "ID", "US", "83814", 47.72844100, -116.78086900, "83814"),
//  AdCity(17782, "Lincoln'S New Salem", "IL", "US", "62659", 40.03111500, -89.78672300, "62659"),
//  AdCity(27166, "Land O'Lakes", "FL", "US", "34637", 28.27820000, -82.46250000, "34637")
//)
implicit val formats = DefaultFormats

def getGoogleGeoCode(size:Int, sleepTimeMillis:Int) = {
  Future.sequence(getAdCities(size).map { adcity =>
    Thread.sleep(sleepTimeMillis)
    Try {
      val address = s"${java.net.URLEncoder.encode(adcity.city, "UTF-8")},${adcity.stateCode},${adcity.postalCode}"
      val googleUrl = s"https://maps.googleapis.com/maps/api/geocode/json?address=${address}&region=us&key=${googleKey}"
      val svc = url(googleUrl)
      val googleRes: Future[JValue] = Http(svc.GET OK as.json4s.Json)

      googleRes.map { j =>
        val geoCodeResult:GoogleGeoCodeResult = (j.extract[GoogleGeoCodeResult])
        geoCodeResult.status match {
          case "OK" => {
            val res:List[(Double,Double)] = geoCodeResult.results.map(g => (g.geometry.location.lat, g.geometry.location.lng))
            val (newLatitude: Double, newLongitude: Double) = res.headOption match {
              case Some(l) => (l._1, l._2)
              case _ => (adcity.latitude, adcity.longitude)
            }
            val newCity = AdCityNew(adcity.city, adcity.stateCode, adcity.postalCode,
              adcity.latitude, adcity.longitude, newLatitude, newLongitude)
            Some(CityResults(compact(render(j)), newCity))
          }
          case _ => {
            println(s"${adcity.city},${adcity.stateCode},${adcity.postalCode}\t=>\t${geoCodeResult.status}")
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

    val filteredCityList:List[CityResults] =
      cityList
        .flatten
//        .filterNot(cr => cr.city.oldLat==cr.city.newLat && cr.city.oldLong == cr.city.newLong)

    val updateQuery:String =
      filteredCityList
        .map(cr => createUpdateStmt(cr.city))
        .mkString("\n")

    val sqlPath = Path("/datadrive/ad_cities.sql")
    val sqlFile = sqlPath.createFile(false)
    sqlFile.appendAll(updateQuery)

    val existingJsonLines:List[String] = Try {
      scala.io.Source.fromFile("/datadrive/ad_cities_json.txt").getLines().toList
    } match {
      case Success(lines) => lines
      case _ => List()
    }
    val existingJsonText:String = existingJsonLines.size match {
      case l if l >= 3 => existingJsonLines.tail.dropRight(1).mkString("\n") + ","
      case _ => ""
    }
    val jsonText:String = existingJsonText + filteredCityList.map(_.json).mkString(",\n")
    val jsonFilePath = Path("/datadrive/ad_cities_json.txt")
    val jsonFile = jsonFilePath.createFile(false)
    if(!jsonText.isEmpty) jsonFile.writeAll("[\n" + jsonText + "\n]")
    Unit
  }
}

getGoogleGeoCode(50, 20)

