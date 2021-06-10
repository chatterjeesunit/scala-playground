import com.wealthminder.common.util.{GeoLocationBoundingBox, GeoUtil}
import com.wealthminder.marketplace.data.dao.{AdCities, AdvisorSearchResults}
import com.wealthminder.marketplace.data.dto.AdCity
import com.wealthminder.marketplace.util.MarketPlaceUtil

import scala.slick.driver.MySQLDriver.simple._
import slick.jdbc.{GetResult, StaticQuery => Q}

case class CitySearch(city:String, state:String, count:Int)
case class City2(postalCode:String,  count:Int, localPostalCodes:List[String])
case class AdvInfo(advisorId:Long, postalCode:String, count:Int = 0)
val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")
val adCities:List[AdCity] = {
  db withSession { implicit session: Session =>
    Query(AdCities).list
  }
}

val fullSearchResults:List[City2] = ({
  db withSession { implicit session: Session =>
    val query = for {
      s <- AdvisorSearchResults if s.stateFilter.isDefined && s.stateFilter =!= ""
    } yield (s.cityFilter, s.stateFilter, s.timeStamp)

    val results = query.take(10000).list
    results
      .groupBy(c => (c._1, c._2))
      .map(k => CitySearch(k._1._1, k._1._2, k._2.size))
      .par.map { cs =>
        val matchingAdCity:Option[AdCity] =  adCities.filter(ac => ac.stateCode.equals(cs.state) && ac.city.equals(cs.city)).headOption
        val geoLocationBoundingBox:Option[GeoLocationBoundingBox] = matchingAdCity.map(ac =>  GeoUtil.getBoundingBox(ac.latitude, ac.longitude, 50.0))
        (matchingAdCity, geoLocationBoundingBox) match {
          case (Some(ac), Some(g)) => {
            val localZipCodes: List[String] =
              adCities.par.filter(adc => adc.latitude <= g.maxLatitude && adc.latitude >= g.minLatitude &&
                adc.longitude <= g.maxLongitude && adc.longitude >= g.minLongitude).map(_.postalCode).toList
            Some(City2(ac.postalCode, cs.count, localZipCodes))
          }
          case (Some(ac), None) => Some(City2(ac.postalCode, cs.count, List(ac.postalCode)))
          case _ => None
        }
      }.toList.flatten
}})

val advInfoList:List[AdvInfo] = db withSession{ implicit session:Session =>
  val query:String = "select ad_advisor_id , postal_code from ad_advisor_info where is_deleted=false and postal_code is not null and postal_code <> '' "
  implicit val getAdvisorResult = GetResult(r => AdvInfo(r.<<, r.<<))
  Q.queryNA[AdvInfo](query).list
}
def getPostalCodeSearchCount(searchResults:List[City2]):Map[String, Int] = searchResults.par.map(s => s.localPostalCodes.map(p => (p -> s.count))).flatten.toList.groupBy(_._1).map(m => m._1 -> m._2.foldRight(0)(_._2 + _))

def getAdvCountMap(searchResults:List[City2], advInfoList:List[AdvInfo]):List[AdvInfo] = {
  val postalCodeSearchCount:Map[String,Int] = getPostalCodeSearchCount(searchResults)
  advInfoList.par.map { a =>
    val advZip = MarketPlaceUtil.zipCode5(a.postalCode)
    a.copy(count = postalCodeSearchCount.getOrElse(advZip, 0))
  }.filterNot(_.count == 0).toList
}
getAdvCountMap(fullSearchResults, advInfoList).mkString("\n")
