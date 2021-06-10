import com.wealthminder.marketplace.util.MarketplaceSecurityUtil

import scala.util.matching.Regex

//Profile Page URL Pattern. Will match to URL like - http://localhost:8080/financial-advisors/profile/michael-gibney/262778
val P1:Regex = """^https?:\/\/([\w\d\.\:]+)\/financial-advisors\/profile\/([\w]+-[\w]+)\/([\d]+)\/?""".r
//Advisor City Page URL Pattern. Will match to URL like - http://localhost:8080/financial-advisors/NJ/riverdale/peter-mckenna/12320
val P2:Regex = """^https?:\/\/([\w\d\.\:]+)\/financial-advisors\/([a-zA-Z]{2})\/([\w]+-?[\w]+)\/([\w]+-[\w]+)\/([\d]+)\/?""".r

val list = List(
  "http://localhost:8080/financial-advisors/advisory-firms/highland-financial-advisors-llc/7798",
  "http://localhost:8080/",
  "http://localhost:8080/financial-advisors/NJ/riverdale/peter-mckenna/12320",
  "http://localhost:8080/financial-advisors/profile/michael-gibney/262778",
  "http://hopkins.wealthminder.com:8080/financial-advisors/profile/michael-gibney/262778",
  "http://localhost:8080/financial-advisors/CA/san-ramon?query=")
list.foreach(s => s match {
  case P1(host,name,advisorId,_*) => System.out.println(s"Profile Page - $host, $name, $advisorId")
  case P2(host, state, city, name, advisorId, _*) => System.out.println(s"Advisor City Page - $host, $state, $city, $name, $advisorId")
  case _ => System.out.println("match not found")
})


val results: List[String] = List(
  "/marketplace/quotes/3e3b32d9-19a3-4709-8e53-de6ac95ded8c/approve/$TOKEN_START:3e3b32d9-19a3-4709-8e53-de6ac95ded8c:test2.sunit@wealthminder.com:TOKEN_END$",
  "/marketplace/quotes/3e3b32d9-19a3-4709-8e53-de6ac95ded8c/reject/$TOKEN_START:3e3b32d9-19a3-4709-8e53-de6ac95ded8c:test2.sunit@wealthminder.com:TOKEN_END$")


val regex:Regex = "/marketplace/quotes/([0-9a-z-]*)/(approve|reject)/\\$TOKEN_START:([0-9a-z-]*):([0-9a-zA-Z@.-]*):TOKEN_END\\$".r

results.map { str =>
  str match {
    case regex(externalId1, action, externalId2, email, _*) => {
      val token: String = MarketplaceSecurityUtil.generatePublicVerificationToken(externalId2, email)
      s"/marketplace/quotes/${externalId1}/${action}/${token}"
    }
    case _ => str
  }
}