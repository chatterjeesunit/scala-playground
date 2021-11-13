import com.playground.json.SlackMessage
import org.json4s._

val jsonString = "{\n  \"text\": \"Another test....\",\n  \"channel\": \"#logtesting\",\n  \"username\":\"Error-Bot\"\n}"

val testobject = new SlackMessage("Testing", "#logtesting","bot")

val jsonObj = parse(jsonString)


