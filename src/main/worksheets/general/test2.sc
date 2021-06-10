import java.lang.reflect.Field

import com.wealthminder.marketplace.data.dao.advisor.PublishedStatus
import com.wealthminder.marketplace.data.dto.advisor.{AdvisorFlags, Disclosure}
import com.wealthminder.marketplace.util.MarketPlaceUtil
import com.wealthminder.webapi.data.DataUtility

import scala.util.Random


val pageNums:List[Int] = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
val totalRecords:Int = 4
pageNums.foreach(p => println((new Random).nextInt(totalRecords)))
val test1:Option[Option[String]] = Some(None)
val test2:Option[Option[String]] = None
val test3:Option[Option[String]] = Some(Some("test"))
test1.flatten
test2.flatten
test3.flatten
val advisorFlags = AdvisorFlags(true,true,true,true,true,false,false,true,true,true,false,true,false,false, true)
val disclosuresMap:Map[String, Disclosure] = Map[String, Disclosure](
  ("hasRegulatoryAction", Disclosure("Regulatory Action", "The advisor has had a regulatory body take action against them.")),
  ("hasCriminalRecord", Disclosure("Criminal Record", "The advisor has been charged and / or convicted of a felony or they have been charged and / or convicted of a misdemeanor related to investments or investment-related business.")),
  ("hasDeclaredBankruptcy", Disclosure("Declared Bankruptcy", "The advisor has either declared bankruptcy personally in the past 10 years or declared bankruptcy for an organization they had control over.")),
  ("hasCivilJudgement", Disclosure("Civil Judgement", "A civil court has awarded a judgment against the advisor related to investments or investment activity.")),
  ("hasBond", Disclosure("Bond", "This advisor has had a bonding company either deny, pay out on or revoke a bond for them.")),
  ("hasJudgment", Disclosure("Judgments / Liens", "This advisor currently has outstanding liens or judgments related to their personal finances.")),
  ("hasRegulatoryInvestigation", Disclosure("Regulatory Investigation", "The advisor is currently the subject of a regulatory investigation, but no final judgment has occurred.")),
  ("hasCustomerComplaints", Disclosure("Customer Complaint", "The advisor has been named as a defendant in a civil case or arbitration involving investment-related activity that is either still pending or the ruling went against the advisor.")),
  ("isTerminatedFromJob", Disclosure("Fired from a Job", "The advisor was terminated from a job for cause or resigned due to allegations of fraud or violations of investment-related statutes and responsibilities."))
)
  disclosuresMap.filter{ p =>
    val field:Field = advisorFlags.getClass.getDeclaredField(p._1)
    field.setAccessible(true)
    field.getBoolean(advisorFlags)
  }.values.map(_.name)


MarketPlaceUtil.zipCode5("")

val publishedStatus:PublishedStatus.Value = PublishedStatus.DRAFT

s"published_status = '${publishedStatus}'"


def convertEmptyStringToNone(x:Option[String]) = x map (_.trim) filterNot (_.isEmpty)
convertEmptyStringToNone(Some(""))
convertEmptyStringToNone(Some(" "))
convertEmptyStringToNone(Some(" abc d "))
convertEmptyStringToNone(Some("test again"))
convertEmptyStringToNone(None)

val e:String = "This request was closed by the system, as the advisor does not requires compliance approval after the latest profile update."
val encodedString:String = java.net.URLEncoder.encode(e, "UTF-8")
val decodedString:String = java.net.URLDecoder.decode(encodedString, "UTF-8")
val decodedString2:String = java.net.URLDecoder.decode(decodedString, "UTF-8")

def zipCodeDispVal(postalCode:Option[String]) : Option[String] =
  postalCode.map(p => p.length match {
    case l if l <= 5 => p
    case _ => s"${p.substring(0,5)}-${p.substring(5)}"
  })
def phoneDispVal(phone:Option[String]) : Option[String] =
  phone.map(p => p.length match {
    case l if l <= 3 => p
    case l if l <=6 => s"${p.substring(0,3)}-${p.substring(3)}"
    case _ => s"${p.substring(0,3)}-${p.substring(3,6)}-${p.substring(6)}"
  })
zipCodeDispVal(None)
zipCodeDispVal(Some("1234"))
zipCodeDispVal(Some("12345"))
zipCodeDispVal(Some("12345678"))
zipCodeDispVal(Some("1234567890"))


phoneDispVal(None)
phoneDispVal(Some("12"))
phoneDispVal(Some("123"))
phoneDispVal(Some("1234"))
phoneDispVal(Some("123456"))
phoneDispVal(Some("12345678"))
phoneDispVal(Some("123456789012"))


DataUtility.asCurrencyFormatted(1223)

MarketPlaceUtil.cityHashCode("Larkspur")

MarketPlaceUtil.cityHashCode("San Francisco")