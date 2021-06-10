import com.wealthminder.marketplace.data.dao.advisor.{AssetRangeEnum, QuoteServiceType}
import com.wealthminder.marketplace.data.dto.advisor.AdvisorQuotePreferenceDTO
import com.wealthminder.webapi.data.USState
import org.json4s.JsonAST.JObject
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

val preference = AdvisorQuotePreferenceDTO(111,
  List(1,4,5,2,9),
  List(USState("CA", "California"), USState("FL", "Florida")),
  Some(AssetRangeEnum.MORE_THAN_100K),
  List(QuoteServiceType.ONETIME),
  true,
  true
)



val jobj = preference.toJsonObject
//compact(render(("name" -> "Surya") ~ ("job" -> "Dev")))
compact(render(jobj))