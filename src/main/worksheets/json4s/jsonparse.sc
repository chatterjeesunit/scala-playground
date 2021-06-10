import org.json4s.JsonAST.JObject
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.json4s.{DefaultFormats, Formats}
implicit def jsonFormats: Formats = DefaultFormats
case class ClaimPageRequest(name:String, email:String, password:String, phoneNumber:String)
val json = parse("""{"name":"Sunit Chatterjee","email":"sunit@wealthminder.com","password":"welcome","phoneNumber":"335-1231222"}""")
val map = json.values
val claimReq = json.extract[ClaimPageRequest]
claimReq.email
claimReq.name
claimReq.password
claimReq.phoneNumber
val postReq =
  ("type" -> "add") ~
    ("id" -> "3211") ~
    ("fields" ->
      ("advisor_id" -> "3211") ~
        ("advisor_crd" -> "3016539") ~
        ("first_name" -> "Sunit") ~
        ("last_name" -> "Chatterjee") ~
        ("firm_name" -> "Digital Cues") ~
        ("description" -> "Sr. Tech Lead at Digital Cues, but working for Wealthminder") ~
        ("advisor_score" -> "99") ~
        ("state_code" -> "CA") ~
        ("city" -> "San Francisco") ~
        ("exams" -> List("S63","S65","S101").map{ e =>
          (e)}) ~
        ("designation" -> List("Accredited Estate Planner","Certified Annuity Advisor","Certified Credit Counselor","Certified Estate Planner","Certified Financial Planner").map{d =>
          (d)})
      )


val jsonString: String = compact(render(List(postReq, postReq)))


val json1:JObject = ("firstName" -> "Avanti") ~ ("lastName" -> "Pande") ~("DOB" -> "2nd Jan 1980")
val json2:JObject = ("field" -> "IT") ~ ("experience" -> "10 yrs") ~("company" -> "Digital Cues")

val json3:JObject = ("personalInfo" -> json1) ~ ("job" -> json2)

val list = List("S63","S65","S101")

compact(render(list))


compact(render(json3))

val testname:Option[String] = Some("Sunit")

compact(render((testname)))




