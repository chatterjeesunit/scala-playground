import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

import com.wealthminder.common.util.{GeoLocationBoundingBox, GeoUtil}
import com.wealthminder.marketplace.data.dao.{AdCities, AdvisorDAO, AdvisorSearchDAO, AdvisorVisitDAO}
import com.wealthminder.marketplace.data.dto.AdCity
import com.wealthminder.marketplace.data.dto.advisor.AdvisorStatistics
import com.wealthminder.marketplace.scheduler.{AdvSearchResult, AdvStatsInfo, AdvVisitsResults}
import com.wealthminder.marketplace.util.MarketPlaceUtil
import com.wealthminder.webapi.Predef._
import com.wealthminder.webapi.data.{Constants, DataCache}
import org.joda.time.DateTime

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.session.Session

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")

val formatter = new SimpleDateFormat("MM/dd/yyyy")
val currentDateTime: DateTime = new DateTime(getSqlRightNow()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0)
val to: Timestamp = new Timestamp(currentDateTime.getMillis)
val from: Timestamp = new Timestamp(currentDateTime.minusDays(1).getMillis)

val advInfos: Vector[AdvStatsInfo] = db withSession { implicit session: Session =>
  AdvisorDAO.getAdvInfoForStatistics(List(195908,222320,138689,242147), false)
}

val searchResults: Vector[AdvSearchResult] = db withSession{implicit session:Session =>
  AdvisorSearchDAO.getAllAdvisorSearches(from,to)
}

val visits: Vector[AdvVisitsResults] = db withSession{implicit session:Session =>
  AdvisorVisitDAO.getAdvisorProfileVisitsFrom(from,to)
}

val adCities: List[AdCity] = DataCache.allCityMapByZip.values.toList.flatten match {
  case cities if cities.nonEmpty => cities
  case _ => { db withSession{ implicit session :Session =>
    Query(AdCities).list()
  }
  }
}

val adCitiesMap: Map[String, List[AdCity]] = adCities.groupBy(ac => (ac.city++ac.stateCode).toLowerCase)

val searchZipMap = searchResults.par.filter(sr =>
  !sr.city.getOrElse("").equals("") && !sr.state.getOrElse("").equals("")).groupBy(sr =>
  formatter.format(sr.ts)).mapValues { srList =>
    srList.flatMap(sr =>
      adCitiesMap.getOrElse((sr.city.getOrElse("")++sr.state.getOrElse("")).toLowerCase, List()).headOption.map(_.postalCode)
    ).toVector
  }.seq.toMap


val advisorPostalCode: Map[Long, String] = advInfos.par.map(a => (a.advisorId, MarketPlaceUtil.zipCode5(a.postalCode))).seq.toMap

val visitsZipMap: Map[String, Vector[String]] =
  visits.par.groupBy(visits =>
    formatter.format(visits.ts)).mapValues { visitList =>
      visitList.flatMap { v =>
        (v.city,v.state) match {
          case (Some(c), Some(st)) => {
            //Visits table has city state information. Then fetch postal code based on city, state
            adCitiesMap.getOrElse((c ++ st).toLowerCase, List()).headOption.map(_.postalCode)
          }
          case _ => {
            //If visit table does not has city, state information, then get advisor primary address's postal code
            advisorPostalCode.get(v.advisorId)
          }
        }
      }.toVector
    }.seq.toMap


val postalCodes:List[String] = (searchZipMap.values.flatten.toSet union visitsZipMap.values.flatten.toSet).toList


val postalCodeMap: Map[String, List[String]] = adCities.map{ ac =>
  val g:GeoLocationBoundingBox = GeoUtil.getBoundingBox(ac.latitude, ac.longitude, 50.0)
  //Get List of local postal codes
  val localPostalCodes:List[String] = adCities.par.filter(c =>
        c.latitude <= g.maxLatitude && c.latitude >= g.minLatitude &&
          c.longitude <= g.maxLongitude && c.longitude >= g.minLongitude).map(_.postalCode).toList
  (ac.postalCode -> localPostalCodes)
}.toMap

System.out.println(s"searchZip = ${searchZipMap.values.flatten}")
System.out.println(s"visitsZip = ${visitsZipMap.values.flatten}")
advInfos.map { advisor =>
  //AllPostalCodes of Advisor
  val advisorZips: List[String] = s"${advisor.postalCode},${advisor.additionalPostalCodes.getOrElse("")}".split(Constants.DELIMITER_COMMA).map(_.trim).toList
  val advisorLocalZips: Set[String] = advisorZips.par.flatMap(zip => postalCodeMap.getOrElse(zip,List(zip))).seq.toSet

  System.out.println(s"advisorId = ${advisor.advisorId}")
  System.out.println(s"\tadvisorZips = ${advisorZips}")
  System.out.println(s"\tadvisorLocalZips = ${advisorLocalZips.size}")
  val localAreaSearches: Long = searchZipMap.values.flatten.count(zip => {
    System.out.println(s"\t\t$zip => ${advisorLocalZips.contains(zip)}")
    advisorLocalZips.contains(zip)
  })
  val localAreaVisits: Long = visitsZipMap.values.flatten.count(zip => {
    System.out.println(s"\t\t$zip => ${advisorLocalZips.contains(zip)}")
    advisorLocalZips.contains(zip)
  })

  System.out.println(s"\tlocalAreaSearches = ${localAreaSearches}")
  System.out.println(s"\tlocalAreaVisits = ${localAreaVisits}")

  AdvisorStatistics(
    None,
    advisor.advisorId,
    -1,
    localAreaSearches,
    localAreaVisits,
    -1,
    -1,
    -1,
    new Date(from.getTime))
}.filterNot(adv => (adv.profileVisitCount == 0 && adv.localAreaSearches == 0 && adv.localAreaVisits == 0 &&
  adv.showedAsSimilarAdvisor == 0 && adv.profileSearches == 0 && adv.featuredAdvisorSearches == 0)).seq
