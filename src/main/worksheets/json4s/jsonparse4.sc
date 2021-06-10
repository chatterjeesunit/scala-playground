import org.json4s.DefaultFormats
import org.json4s.JsonAST.{JField, JObject, JString}
import org.json4s.native.JsonMethods._
implicit val formats = DefaultFormats

//
//val advIndexJson = "[\n  {\n    \"type\": \"add\",\n    \"id\": \"ADV5\",\n    \"fields\": {\n      \"advisor_id\": \"5\",\n      \"advisor_crd\": \"1460392\",\n      \"first_name\": \"Vishwas\",\n      \"last_name\": \"Jagetia\",\n      \"middle_name\": \"Leigh\",\n      \"firm_name\": \"Gwn Securities Inc.\",\n      \"description\": \"\",\n      \"exams\": [\n        \"S63\",\n        \"S65\",\n        \"S101\"\n      ],\n      \"designation\": [\n        \"Accredited Estate Planner\",\n        \"Certified Annuity Advisor\",\n        \"Certified Credit Counselor\",\n        \"Certified Estate Planner\",\n        \"Certified Financial Planner\"\n      ],\n      \"advisor_score\": \"95\",\n      \"state_code\": \"FL\",\n      \"city\": \"Palm Beach Gardens\"\n    }\n  },\n  {\n    \"type\": \"add\",\n    \"id\": \"99\",\n    \"fields\": {\n      \"advisor_id\": \"99\",\n      \"advisor_crd\": \"1460392\",\n      \"first_name\": \"Barbara\",\n      \"last_name\": \"White\",\n      \"middle_name\": \"Lee\",\n      \"firm_name\": \"Lpl Financial LLC\",\n      \"description\": \"testing for securities\",\n      \"exams\": [\n        \"S65\",\n        \"S99\"\n      ],\n      \"designation\": [\n        \"Certified Financial Planner\"\n      ],\n      \"advisor_score\": \"93\",\n      \"state_code\": \"MA\",\n      \"city\": \"Boston\"\n    }\n  },\n  {\n    \"type\": \"add\",\n    \"id\": \"5022\",\n    \"fields\": {\n      \"advisor_id\": \"5022\",\n      \"advisor_crd\": \"1673192\",\n      \"first_name\": \"Barbara\",\n      \"last_name\": \"Bowman\",\n      \"middle_name\": \"Jo\",\n      \"firm_name\": \"Lpl Financial LLC\",\n      \"description\": \"test\",\n      \"exams\": [\n        \"S63\",\n        \"S45\"\n      ],\n      \"designation\": [\n        \"Certified Estate Planner\",\n        \"Certified Financial Planner\"\n      ],\n      \"advisor_score\": \"93\",\n      \"state_code\": \"MA\",\n      \"city\": \"Boston\"\n    }\n  },\n  {\n    \"type\": \"add\",\n    \"id\": \"9933\",\n    \"fields\": {\n      \"advisor_id\": \"9933\",\n      \"advisor_crd\": \"1164392\",\n      \"first_name\": \"Dan\",\n      \"last_name\": \"White\",\n      \"middle_name\": \"Lee\",\n      \"firm_name\": \"Lpl Financial LLC\",\n      \"description\": \"testing for securities\",\n      \"exams\": [\n        \"S65\",\n        \"S39\"\n      ],\n      \"designation\": [\n        \"Accredited Estate Planner\"\n      ],\n      \"advisor_score\": \"93\",\n      \"state_code\": \"MA\",\n      \"city\": \"Boston\"\n    }\n  },\n  {\n    \"type\": \"add\",\n    \"id\": \"9329\",\n    \"fields\": {\n      \"advisor_id\": \"9329\",\n      \"advisor_crd\": \"1460392\",\n      \"first_name\": \"John\",\n      \"last_name\": \"White\",\n      \"middle_name\": \"Lee\",\n      \"firm_name\": \"charles schwab\",\n      \"description\": \"testing for securities\",\n      \"exams\": [\n        \"S65\",\n        \"S99\"\n      ],\n      \"designation\": [\n        \"Accredited Estate Planner\",\n        \"Certified Annuity Advisor\",\n        \"Certified Credit Counselor\",\n        \"Certified Estate Planner\",\n        \"Certified Financial Planner\"\n      ],\n      \"advisor_score\": \"93\",\n      \"state_code\": \"CA\",\n      \"city\": \"San Francisco\"\n    }\n  }\n]"
//val result = parse(advIndexJson) removeField {
//  case JField("type", _) => true
//  case JField("fields", _) => true
//  case _ => false
//}
//result.extract[List[Map[String, String]]].map(m => m.values.toList).flatten.map(str => str.replace("ADV", ""))
//
//
//case class Person (name:String, team:String) {
//  def toJsonObject: JValue = ("name" -> name) ~ ("team" -> team)
//}
//
//val personJson = """[{"name":"Sunit", "team":"Wealthminder"}, {"name":"Avanti", "team":"Team Fit"}, {"name":"Harshal", "team":"ADP"}]"""
//
//val persons:List[Person] = parse(personJson).extract[List[Person]]
//
//
//compact(render(persons.map(_.toJsonObject)))


val fbJson = """{"id":"1286126034754226","first_name":"Sunit","last_name":"Chatterjee","picture":{"data":{"is_silhouette":false,"url":"https:\/\/fbcdn-profile-a.akamaihd.net\/hprofile-ak-xat1\/v\/t1.0-1\/p50x50\/10417554_871769706189863_2235857306635072614_n.jpg?oh=62eba71f6636d955b051e4b557889f7a&oe=586AC987&__gda__=1483484355_660af92fc4aa3fc0ebaa3eea733c64f9"}},"email":"chatterjeesunit\u0040yahoo.com"}"""
case class FBPictureData(data: FBPicture)
case class FBPicture(is_silhouette: Boolean, url: String)
case class FBAuthResponse(email:String, id: String, first_name: String, last_name: Option[String], picture: FBPictureData)
val fbParsed = parse(fbJson)
fbParsed.extract[FBAuthResponse]

val fbParsedGen = parse(fbJson).transformField {
  case JField("email", JString(email)) => ("emailAddress", JString(email))
  case JField("first_name", JString(firstName)) => ("firstName", JString(firstName))
  case JField("last_name", JString(lastName)) => ("lastName", JString(lastName))
  case JField("picture", picture:JObject) => ("pictureUrl", JString((picture \ "data" \ "url").extract[String]))
}
fbParsedGen.extract[GenericAuthResponse]

val liJson = "{\n  \"emailAddress\": \"chatterjeesunit@yahoo.com\",\n  \"firstName\": \"Sunit\",\n  \"id\": \"YQrP3fpJss\",\n  \"lastName\": \"Chatterjee\",\n  \"pictureUrl\": \"https://media.licdn.com/mpr/mprx/0_Gz7oEyHhmeD94xBBQcRXbet3adGwYuXcicJk2dX3Ss_oRyccIzBET063eujwRDLsazcwCDdTG0uEZeXbwQE9CeHSb0uIZePRwQEdWWj8uOMbtYebFcpbdj1lFEnRAeXoGLa6aRxT1oz\",\n  \"pictureUrls\": {\n    \"_total\": 1,\n    \"values\": [\"https://media.licdn.com/mpr/mprx/0_1JVRzpKSt5agZzislUFMPkueAQ7NmRDs_U6M8GKIKIfq0c8JtyFj64lI3NGA0USML0FOXnrmtymljoDnAtwNGFKDLymAjolsNtwJBpuSKQ04jgh1N9U4j-0G6a\"]\n  }\n}"

case class GenericAuthResponse(emailAddress:String, id: String, firstName: String, lastName: Option[String], pictureUrl: Option[String])
val liparse = parse(liJson)
  liparse.extract[GenericAuthResponse]
