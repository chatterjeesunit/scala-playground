import org.json4s.{DefaultFormats, Formats}
import org.json4s.JsonAST.{JField, JInt, JString}
import org.json4s.native.JsonMethods._
implicit def jsonFormats: Formats = DefaultFormats

case class Test(id: Long, quoteId: Option[String], guid: Option[String])

var m18 = " { \"id\": 1, \"quoteId\": 100 }"
var m19 = """ { "id": 1, "quoteId": "surya" } """
var m20 = """ { "id": 1, "guid": "surya" } """
var m21 = """ { "id": "1", "guid": "surya" } """


def transformField(jsonStr: String): Test = {
  val jobj=  parse(jsonStr) transformField {
    case JField("quoteId", JInt(id)) => ("quoteId", JString(id.toString))
  }
  jobj.extract[Test]
}

transformField(m18)
transformField(m19)
transformField(m20)
transformField(m21)

parse(m18).extract[Test]
