import java.text.Normalizer

import com.wealthminder.marketplace.data.dto.DirectoryHelper
import com.wealthminder.marketplace.security.MarketPlaceAuthorizationManager
import com.wealthminder.marketplace.util.MarketPlaceUtil
import com.wealthminder.webapi.data.Constants
//val cities = List("Mc Lean", "Mclean", "Reston")
//
//"\n" + cities.map(c => c-> MarketPlaceUtil.cityHashCode(c)).mkString("\n")
//
//
//val str1 = "http://localhost:8080\\index.html"
//val str2 = """http://localhost:8080\index.html"""
//
//
//val strA:Option[String] = Some("abc,def")
//val strB:Option[String] = None
//strA.flatMap(_.split(",").toList.lift(1))
//strB.flatMap(_.split(",").toList.lift(1))
//
//List("abc", "def", "123").lift(1)
//List("abc", "def", "123").lift(2)
//
//List("abc").lift(1)
//
//
//strA.flatMap(_.split(",").toList.headOption)
//
//def advisorZips(postalCode:String,additionalPostalCodes: Option[String]) : List[String] = postalCode :: additionalPostalCodes.map(zip => zip.split(Constants.DELIMITER_COMMA).map(_.trim).toList).getOrElse(List())
//
//advisorZips("5555", None)
//advisorZips("5555", Some("666, 7777, 888"))
//advisorZips("", Some(""))
//advisorZips("", None)
//advisorZips("", Some("545"))
//
////
////val name = "Joshua D. Döäüter, CFP® AIF®"
////val wmName = DirectoryHelper.normalizeNameForUrl(name)
////val normalizedName = Normalizer.normalize(wmName, Normalizer.Form.NFD);
////val finalResult = normalizedName.replaceAll("[^\\x00-\\x7F]", "");
//
//val stateParam: Option[String] = Some("queryId=1&sigin=true")
//
//stateParam.map { s =>
//  s.split("&").map { s =>
//    val list = s.split("=").toList
//    (list.head -> list.tail.headOption.getOrElse(""))
//  }.toMap
//}.getOrElse(Map())
//
//List(
//  Some("1111").map(q => s"quoteId=${q}"),
//  Some("true").map(cm => s"isCreateMode=${cm}")
//).flatten match {
//  case List() => None
//  case l => Some(l.mkString("&"))
//}
//
//
//List(
//  None.map(q => s"quoteId=${q}"),
//  Some("true").map(cm => s"isCreateMode=${cm}")
//).flatten match {
//  case List() => None
//  case l => Some(l.mkString("&"))
//}
//
//
//
//List(
//  Some("1111").map(q => s"quoteId=${q}"),
//  None.map(cm => s"isCreateMode=${cm}")
//).flatten match {
//  case List() => None
//  case l => Some(l.mkString("&"))
//}
//
//
//
//List(
//  None.map(q => s"quoteId=${q}"),
//  None.map(cm => s"isCreateMode=${cm}")
//).flatten match {
//  case List() => None
//  case l => Some(l.mkString("&"))
//}
//

MarketPlaceAuthorizationManager.parseToken("TUtBVEtOfDBEMjA0QkY3OEFGQTA3OUI2ODFCQTFGREU4MDVFQ0I5ODcyQjlBNzYxODY5RjkyRA==")