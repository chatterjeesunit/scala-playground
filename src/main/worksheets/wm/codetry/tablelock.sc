import java.sql.Date
import com.wealthminder.marketplace.data.dao.advisor.AdvisorStatisticsObject
import com.wealthminder.marketplace.data.dto.advisor.AdvisorStatistics
import com.wealthminder.webapi.Predef._
import scala.slick.jdbc.{StaticQuery => Q}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.session.Session

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")

db.withSession{ implicit session:Session =>
  session withTransaction {
    (Q.u + "LOCK TABLE ad_advisor_statistics WRITE").execute

    AdvisorStatisticsObject.insert(AdvisorStatistics(None, 34234l, 1l, 1l,  50l, new Date(getSqlRightNow.getTime)))

    Thread.sleep(10000)
    (Q.u + "UNLOCK TABLE").execute

  }

}
