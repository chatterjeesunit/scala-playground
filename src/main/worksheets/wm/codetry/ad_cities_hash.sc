import com.wealthminder.marketplace.data.dao.AdCities
import com.wealthminder.marketplace.data.dto.AdCity
import com.wealthminder.marketplace.util.MarketPlaceUtil

import slick.driver.MySQLDriver.simple._

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")
val adCities:List[AdCity] = {
  db withSession { implicit session: Session =>
    Query(AdCities).list
  }
}

adCities
  .map(_.city)
  .distinct
  .map(c => (MarketPlaceUtil.cityHashCode(c) -> c))
  .groupBy(k => k._1)
  .map(m => m._1 -> m._2.map(_._2).distinct)
  .filter(m => m._2.size > 1)
  .mkString("\n")


adCities
  .map(_.stateCode)
  .distinct
  .map(s => (MarketPlaceUtil.stateHashCode(s) -> s))
  .groupBy(k => k._1)
  .map(m => m._1 -> m._2.map(_._2).distinct)
  .filter(m => m._2.size > 1)
  .mkString("\n")


adCities
  .map(_.postalCode)
  .distinct
  .map(p => (MarketPlaceUtil.postalCodeHash(MarketPlaceUtil.zipCode5(p)) -> p))
  .groupBy(k => k._1)
  .map(m => m._1 -> m._2.map(_._2).distinct)
  .filter(m => m._2.size > 1)
  .mkString("\n")