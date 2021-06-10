import com.wealthminder.marketplace.advisor.{AccountStatus, ClaimStatus}
import com.wealthminder.marketplace.data.dao.advisor.PublishedStatus
import com.wealthminder.marketplace.data.dao.{AdCacheLocations, AdFirms, AdvisorDAO}
import com.wealthminder.marketplace.data.dto.{AdAdvisorExam, AdCity}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.session.Session
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")

case class AdAdvisorInfoBasic ( adAdvisorId: Long, firstName: String, lastName: String,
  advisorCrdNumber: Long,
  currentFirmCrdNumber: Long,
  city: Option[String],
  stateCode: Option[String],
  postalCode: Option[String],
  isDeleted: Option[Boolean],
  isQualifiedAdvisor: Boolean,
  claimStatus: ClaimStatus.Value,
  accountStatus: AccountStatus.Value,
  externalUserId: String,
  publishedStatus: PublishedStatus.Value,
  permissionGroupId: Long,
  permissions: String,
  advisorExperience: Option[Int])
  
object AdAdvisorInfoBasics extends Table[AdAdvisorInfoBasic]("ad_advisor_info") {
  def adAdvisorId = column[Long]("ad_advisor_id")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def advisorCrdNumber = column[Long]("advisor_crd_number")
  def currentFirmCrdNumber = column[Long]("current_firm_crd_number")
  def city = column[Option[String]]("city", O.Nullable)
  def stateCode = column[Option[String]]("state_code", O.Nullable)
  def postalCode = column[Option[String]]("postal_code", O.Nullable)
  def isDeleted = column[Option[Boolean]]("is_deleted")
  def isQualifiedAdvisor = column[Boolean]("qualified_advisor")
  def claimStatus = column[ClaimStatus.Value]("claim_status", O.NotNull)
  def accountStatus = column[AccountStatus.Value]("account_status", O.NotNull)
  def externalUserId = column[String]("external_user_id", O.NotNull)
  def publishedStatus = column[PublishedStatus.Value]("published_status", O.NotNull)
  def permissionGroupId = column[Long]("ad_permission_group_id", O.NotNull)
  def permissions = column[String]("permissions", O.NotNull)
  def advisorExperience = column[Option[Int]]("experience_yrs", O.Nullable)

  def * = adAdvisorId ~ firstName ~ lastName  ~ advisorCrdNumber ~ currentFirmCrdNumber ~ city ~ stateCode ~ postalCode ~
    isDeleted ~ isQualifiedAdvisor ~ claimStatus ~ accountStatus ~ externalUserId ~ publishedStatus ~
    permissionGroupId ~ permissions ~ advisorExperience  <> (AdAdvisorInfoBasic, AdAdvisorInfoBasic.unapply _)
}

type advType = (Long, Long, Option[Int])
type firmType = (Long, String, Double)

def getAllAdvisorsForCity(city:String, state:String):List[advType] =  {
  db withSession { implicit session: Session =>
    Query(AdAdvisorInfoBasics).filter(a => a.city === city && a.stateCode === state).map(a => (a.adAdvisorId, a.currentFirmCrdNumber, a.advisorExperience)).list
  }
}

def getAllFirmsForCity(city:String, state:String):List[firmType] =  {
  db withSession { implicit session: Session =>
    Query(AdFirms).filter(af => af.city === city && af.stateCode === state).sortBy(_.aumAmountTotal).take(5).map(f => (f.adFirmId, f.legalName, f.aumAmountTotal)).list
  }
}

val time1 = System.currentTimeMillis()

  val advisors = getAllAdvisorsForCity("New York", "NY")
val time2 = System.currentTimeMillis()

println(s"${time2 - time1} milliseconds")
  val firms = getAllFirmsForCity("New York", "NY")

val time3 = System.currentTimeMillis()

println(s"${time3 - time2} milliseconds")

  val advisorIds:List[Long] = advisors.par.map(_._1).toList
  println(advisorIds.size)
  println(firms.size)


//  val exp = advisors.par.map(a => a._1 -> a._3).toList.toMap
//  val examMap:Map[Long,List[AdAdvisorExam]] = db withSession {implicit session =>
//    AdvisorDAO.getMultipleAdvisorExams(advisorIds)
//  }

val time4 = System.currentTimeMillis()

println(s"${time4 - time3} milliseconds")

def getExperience(advisorId:Long, experience:Option[Int], exams:List[AdAdvisorExam]):Int = {
  experience match {
    case Some(e) => e
    case _ => 0
  }
}