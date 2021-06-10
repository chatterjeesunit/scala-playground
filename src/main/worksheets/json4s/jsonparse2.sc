import java.sql.{Date, Timestamp}
import javax.swing.JList

import com.wealthminder.webapi.data.DataUtility
import org.json4s.JsonAST._
import org.json4s.native.JsonMethods._


def convertToDate(time:Long):String = {
  val timestamp:Timestamp  = new Timestamp(time*1000)
  val date:Date = new Date(timestamp.getTime)
  DataUtility.convertToJsonDate(date)
}
def renameDescription(description:String):String = {
  description
    .replace("silver_monthly_v1", "Silver Monthly plan")
    .replace("silver_yearly_v1", "Silver Yearly plan")
    .replace("gold_monthly_v1", "Gold Monthly plan")
    .replace("gold_yearly_v1", "Gold Yearly plan")
    .replace("plat_monthly_v1", "Platinum Monthly plan")
    .replace("plat_yearly_v1", "Platinum Yearly plan")
}
val json:String = "{\n    \"object\": \"invoice\",\n    \"amount_due\": 24674,\n    \"application_fee\": null,\n    \"attempt_count\": 0,\n    \"attempted\": false,\n    \"charge\": null,\n    \"closed\": false,\n    \"currency\": \"usd\",\n    \"customer\": \"cus_7zpJ7bAVVkN81T\",\n    \"date\": 1459252659,\n    \"description\": null,\n    \"discount\": null,\n    \"ending_balance\": null,\n    \"forgiven\": false,\n    \"lines\": {\n        \"object\": \"list\",\n        \"data\": [\n            {\n                \"id\": \"ii_17kAsPIARnQVa3IL073hjP1s\",\n                \"object\": \"line_item\",\n                \"amount\": -4836,\n                \"currency\": \"usd\",\n                \"description\": \"Unused time on silver_monthly_v1 after 01 Mar 2016\",\n                \"discountable\": false,\n                \"livemode\": false,\n                \"metadata\": {},\n                \"period\": {\n                    \"start\": 1456828633,\n                    \"end\": 1459252659\n                },\n                \"plan\": {\n                    \"id\": \"silver_monthly_v1\",\n                    \"object\": \"plan\",\n                    \"amount\": 4999,\n                    \"created\": 1454936096,\n                    \"currency\": \"usd\",\n                    \"interval\": \"month\",\n                    \"interval_count\": 1,\n                    \"livemode\": false,\n                    \"metadata\": {},\n                    \"name\": \"silver_monthly_v1\",\n                    \"statement_descriptor\": null,\n                    \"trial_period_days\": null\n                },\n                \"proration\": true,\n                \"quantity\": 1,\n                \"subscription\": \"sub_7zpJqcawCIyQBi\",\n                \"type\": \"invoiceitem\"\n            },\n            {\n                \"id\": \"ii_17kAsPIARnQVa3ILSKQyT0qo\",\n                \"object\": \"line_item\",\n                \"amount\": 14511,\n                \"currency\": \"usd\",\n                \"description\": \"Remaining time on plat_monthly_v1 after 01 Mar 2016\",\n                \"discountable\": false,\n                \"livemode\": false,\n                \"metadata\": {},\n                \"period\": {\n                    \"start\": 1456828633,\n                    \"end\": 1459252659\n                },\n                \"plan\": {\n                    \"id\": \"plat_monthly_v1\",\n                    \"object\": \"plan\",\n                    \"amount\": 14999,\n                    \"created\": 1454936203,\n                    \"currency\": \"usd\",\n                    \"interval\": \"month\",\n                    \"interval_count\": 1,\n                    \"livemode\": false,\n                    \"metadata\": {},\n                    \"name\": \"plat_monthly_v1\",\n                    \"statement_descriptor\": null,\n                    \"trial_period_days\": null\n                },\n                \"proration\": true,\n                \"quantity\": 1,\n                \"subscription\": \"sub_7zpJqcawCIyQBi\",\n                \"type\": \"invoiceitem\"\n            },\n            {\n                \"id\": \"sub_7zpJqcawCIyQBi\",\n                \"object\": \"line_item\",\n                \"amount\": 14999,\n                \"currency\": \"usd\",\n                \"description\": null,\n                \"discountable\": true,\n                \"livemode\": false,\n                \"metadata\": {},\n                \"period\": {\n                    \"start\": 1459252659,\n                    \"end\": 1461931059\n                },\n                \"plan\": {\n                    \"id\": \"plat_monthly_v1\",\n                    \"object\": \"plan\",\n                    \"amount\": 14999,\n                    \"created\": 1454936203,\n                    \"currency\": \"usd\",\n                    \"interval\": \"month\",\n                    \"interval_count\": 1,\n                    \"livemode\": false,\n                    \"metadata\": {},\n                    \"name\": \"plat_monthly_v1\",\n                    \"statement_descriptor\": null,\n                    \"trial_period_days\": null\n                },\n                \"proration\": false,\n                \"quantity\": 1,\n                \"subscription\": null,\n                \"type\": \"subscription\"\n            }\n        ],\n        \"has_more\": false,\n        \"total_count\": 3,\n        \"url\": \"/v1/invoices/upcoming/lines?customer=cus_7zpJ7bAVVkN81T&subscription=sub_7zpJqcawCIyQBi&subscription_plan=plat_monthly_v1\"\n    },\n    \"livemode\": false,\n    \"metadata\": {},\n    \"next_payment_attempt\": 1459256259,\n    \"paid\": false,\n    \"period_end\": 1459252659,\n    \"period_start\": 1456747059,\n    \"receipt_number\": null,\n    \"starting_balance\": 0,\n    \"statement_descriptor\": null,\n    \"subscription\": \"sub_7zpJqcawCIyQBi\",\n    \"subscription_proration_date\": 1456828633,\n    \"subtotal\": 24674,\n    \"tax\": null,\n    \"tax_percent\": null,\n    \"total\": 24674,\n    \"webhooks_delivered_at\": null,\n    \"payment\": null\n}"
val parsedJson:JValue = parse(json)
val updatedJson = parsedJson removeField {
  case JField("object", _) => true
  case JField("application_fee", _) => true
  case JField("attempt_count", _) => true
  case JField("attempted", _) => true
  case JField("charge", _) => true
  case JField("closed", _) => true
  case JField("discount", _) => true
  case JField("ending_balance", _) => true
  case JField("forgiven", _) => true
  case JField("discountable", _) => true
  case JField("livemode", _) => true
  case JField("metadata", _) => true
  case JField("statement_descriptor", _) => true
  case JField("trial_period_days", _) => true
  case JField("proration", _) => true
  case JField("quantity", _) => true
  case JField("type", _) => true
  case JField("paid", _) => true
  case JField("receipt_number", _) => true
  case JField("starting_balance", _) => true
  case JField("subtotal", _) => true
  case JField("tax", _) => true
  case JField("tax_percent", _) => true
  case JField("webhooks_delivered_at", _) => true
  case JField("payment", _) => true
  case _ => false
}
val finalJson = updatedJson transformField {
  case JField("amount_due", JInt(amt)) => ("amountDue", JDouble(amt.toDouble/100))
  case JField("amount", JInt(amt)) => ("amount", JDouble(amt.toDouble/100))
  case JField("total", JInt(amt)) => ("total", JDouble(amt.toDouble/100))
  case JField("period_end", JInt(s)) => ("endDate", JString(convertToDate(s.toLong)))
  case JField("period_start", JInt(s)) => ("startDate", JString(convertToDate(s.toLong)))
  case JField("start", JInt(s)) => ("startDate", JString(convertToDate(s.toLong)))
  case JField("end", JInt(s)) => ("endDate", JString(convertToDate(s.toLong)))
  case JField("date", JInt(s)) => ("date", JString(convertToDate(s.toLong)))
  case JField("created", JInt(s)) => ("created", JString(convertToDate(s.toLong)))
  case JField("description", JString(s)) => ("description", JString(renameDescription(s)))
  case JField("date", JInt(s)) => ("date", JString(convertToDate(s.toLong)))
}

pretty(render(finalJson))

val json2 = """{
              "name":"proposal_detail",
              "content":"<p>Hey Mr.Test3Sas,</p><br><br><p>I would like to submit my proposal on this because I have decent enough experience in suggesting and driving people in the correct direction. But before that I would also like to understand your needs what exactly you want to proceed with.</p><br><br><p>Could you please provide me the details on the below:</p><br><br><ul><br>	<li>What are you exactly looking for?</li><br>	<li>What are your deadlines on your expectations</li><br></ul><br><br><p>I didn&#39;t add any attachments sorry&nbsp;</p><br><br><p>Thanks,</p><br><br><p>John Thamminaina</p><br>"
              }"""
parse(json2)


val json3 = parse("{\"results\":[{\"address_components\":[{\"long_name\":\"Medford\",\"short_name\":\"Medford\",\"types\":[\"locality\",\"political\"]},{\"long_name\":\"Grant County\",\"short_name\":\"Grant County\",\"types\":[\"administrative_area_level_2\",\"political\"]},{\"long_name\":\"Oklahoma\",\"short_name\":\"OK\",\"types\":[\"administrative_area_level_1\",\"political\"]},{\"long_name\":\"United States\",\"short_name\":\"US\",\"types\":[\"country\",\"political\"]},{\"long_name\":\"73759\",\"short_name\":\"73759\",\"types\":[\"postal_code\"]}],\"formatted_address\":\"Medford, OK 73759, USA\",\"geometry\":{\"bounds\":{\"northeast\":{\"lat\":36.8179728,\"lng\":-97.7248429},\"southwest\":{\"lat\":36.785653,\"lng\":-97.75015599999999}},\"location\":{\"lat\":36.8069729,\"lng\":-97.73366360000001},\"location_type\":\"APPROXIMATE\",\"viewport\":{\"northeast\":{\"lat\":36.8179728,\"lng\":-97.7248429},\"southwest\":{\"lat\":36.785653,\"lng\":-97.75015599999999}}},\"place_id\":\"ChIJbSauyjMcsIcRDVjDRxsRG0Y\",\"types\":[\"locality\",\"political\"]}],\"status\":\"OK\"}")
val result:List[(Double,Double)] = for {
  JObject(result) <- json3
  JField("geometry", JObject(geometry))  <- result
  JField("location", JObject(location))  <- geometry
  JField("lat", JDouble(lat))  <- location
  JField("lng", JDouble(lng))  <- location
} yield (lat,lng)

result.head