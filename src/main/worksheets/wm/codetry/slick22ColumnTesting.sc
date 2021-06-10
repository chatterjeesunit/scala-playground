import java.sql.Timestamp
import com.wealthminder.marketplace.data.dao.advisor.{AdvisorCompensationType, PublishedStatus}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.JdbcBackend.{Database, Session}
import scala.slick.collection.heterogenous._
case class AdAdvisorUserTest (
  adAdvisorUserId:Long = -1,
  adAdvisorId:Long,
  signUpEmail:String,
  firstName:Option[String],
  lastName:Option[String],
  middleName:Option[String],
  description:Option[String] = None,
  currentFirmCrdNumber:Option[Long],
  streetAddress:Option[String] = None,
  city:Option[String] = None,
  stateCode:Option[String] = None,
  postalCode:Option[String] = None,
  country:Option[String] = None,
  phoneNumber:Option[String],
  contactEmail:Option[String],
  hasPhoto:Boolean = false,
  externalUserId:String,
  passwordHash:Option[String] = None,
  website:Option[String] = None,
  publishedStatus:PublishedStatus.Value,
  pricingModel:Option[String],
  investmentStrategy: Option[String],
  firmDisclaimer:Option[String],
  planningProcessDescription:Option[String],
  hasFirmLogo:Boolean,
  permissionGroupId:Long,
  permissions:String,
  stripeId : Option[String],
  forwardEmails : Boolean,
  complianceRequired:Boolean,
  compliancePersonEmail:Option[String],
  passwordRecoveryHash:Option[String] = None,
  passwordRecoveryRequestDate:Option[Timestamp] = None,
  lastPasswordChangeDate:Option[Timestamp] = None,
  advisorExperience:Option[Int] = None,
  compensationType:Option[AdvisorCompensationType.Value] = None,
  disclosureResponse :Option[String] = None,
  hideFirmDetails : Option[Boolean] = None
)
class AdAdvisorUsersTest(tag:Tag) extends Table[AdAdvisorUserTest](tag, "ad_advisor_user") {
  def adAdvisorUserId = column[Long]("ad_advisor_user_id", O.PrimaryKey, O.AutoInc)
  def adAdvisorId = column[Long]("ad_advisor_id", O.NotNull)
  def signUpEmail = column[String]("signup_email", O.NotNull)
  def firstName = column[Option[String]]("first_name")
  def lastName = column[Option[String]]("last_name")
  def middleName = column[Option[String]]("middle_name")
  def description = column[Option[String]]("description")
  def currentFirmCrdNumber = column[Option[Long]]("current_firm_crd_number")
  def streetAddress = column[Option[String]]("street_address")
  def city = column[Option[String]]("city")
  def stateCode = column[Option[String]]("state_code")
  def postalCode = column[Option[String]]("postal_code")
  def country = column[Option[String]]("country")
  def phoneNumber = column[Option[String]]("phone_number")
  def contactEmail = column[Option[String]]("contact_email")
  def hasPhoto = column[Boolean]("has_photo")
  def externalUserId = column[String]("external_user_id", O.NotNull)
  def passwordHash = column[Option[String]]("password_hash")
  def website = column[Option[String]]("website")
  def publishedStatus = column[PublishedStatus.Value]("published_status", O.NotNull)
  def pricingModel = column[Option[String]]("pricing_model")
  def investmentStrategy = column[Option[String]]("investment_strategy")
  def firmDisclaimer = column[Option[String]]("firm_disclaimer")
  def planningProcessDescription = column[Option[String]]("planning_process_description")
  def hasFirmLogo = column[Boolean]("has_firm_logo")
  def permissionGroupId = column[Long]("ad_permission_group_id", O.NotNull)
  def permissions = column[String]("permissions", O.NotNull)
  def advisorStripeId = column[Option[String]]("advisor_stripe_id")
  def forwardEmails = column[Boolean]("forward_email", O.NotNull, O.Default(false))
  def complianceRequired = column[Boolean]("compliance_required", O.NotNull, O.Default(false))
  def compliancePersonEmail = column[Option[String]]("compliance_person_email")
  def passwordRecoveryHash = column[Option[String]]("password_recovery_hash")
  def passwordRecoveryRequestDate = column[Option[Timestamp]]("password_recovery_request_date")
  def lastPasswordChangeDate = column[Option[Timestamp]]("last_password_change_date")
  def advisorExperience = column[Option[Int]]("experience_yrs")
  def compensationType = column[Option[AdvisorCompensationType.Value]]("compensation_type")
  def disclosureResponse = column[Option[String]]("disclosure_response")
  def hideFirmDetails = column[Option[Boolean]]("hide_firm_details")

  def * = (adAdvisorUserId :: adAdvisorId :: signUpEmail ::  firstName :: lastName ::  middleName :: description::
            currentFirmCrdNumber:: streetAddress:: city:: stateCode:: postalCode:: country:: phoneNumber:: contactEmail::
            hasPhoto:: externalUserId:: passwordHash:: website:: publishedStatus:: pricingModel:: investmentStrategy::
            firmDisclaimer:: planningProcessDescription:: hasFirmLogo:: permissionGroupId:: permissions:: advisorStripeId::
            forwardEmails:: complianceRequired:: compliancePersonEmail:: passwordRecoveryHash:: passwordRecoveryRequestDate::
            lastPasswordChangeDate:: advisorExperience:: compensationType:: disclosureResponse:: hideFirmDetails :: HNil
    ).shaped <> (
    { case x:HList => new AdAdvisorUserTest(
        x(0), x(1), x(2), x(3),x(4), x(5),x(6), x(7),x(8), x(9),
      x(10), x(11), x(12), x(13),x(14), x(15),x(16), x(17),x(18), x(19),
      x(20), x(21), x(22), x(23),x(24), x(25),x(26), x(27),x(28), x(29),
      x(30), x(31), x(32), x(33),x(34), x(35),x(36), x(37))},
    { x: AdAdvisorUserTest => Option(
        x.adAdvisorUserId :: x.adAdvisorId :: x.signUpEmail ::  x.firstName :: x.lastName :: x.middleName :: x.description::
          x.currentFirmCrdNumber:: x.streetAddress:: x.city:: x.stateCode:: x.postalCode:: x.country:: x.phoneNumber:: x.contactEmail::
          x.hasPhoto:: x.externalUserId:: x.passwordHash:: x.website:: x.publishedStatus:: x.pricingModel:: x.investmentStrategy::
          x.firmDisclaimer:: x.planningProcessDescription:: x.hasFirmLogo:: x.permissionGroupId:: x.permissions:: x.stripeId::
          x.forwardEmails:: x.complianceRequired:: x.compliancePersonEmail:: x.passwordRecoveryHash:: x.passwordRecoveryRequestDate::
          x.lastPasswordChangeDate:: x.advisorExperience:: x.compensationType:: x.disclosureResponse:: x.hideFirmDetails :: HNil
      )}
    )
}
val db = Database.forURL( url="jdbc:mysql://localhost:3306/wmdev", user="wmdev", password = "wmdev", driver = "com.mysql.jdbc.Driver")
val baseQuery = TableQuery[AdAdvisorUsersTest](new AdAdvisorUsersTest(_))
db withSession{ implicit session:Session =>
  val q = for {
    a <- baseQuery if a.adAdvisorId inSet(List(338644l, 44823l))
  } yield a
  q.list
}

