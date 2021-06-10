import com.wealthminder.webapi.data.DataCache._

import scala.util.{Failure, Success, Try}

val botlists = Try {
  scala.io.Source.fromFile("/home/sunitc/source/wealthminder/webapiapp/src/main/resources/botlist.txt").getLines.toList
}match {
  case Success(list) => {
    list
  }
  case Failure(f) => {
    System.out.println(s"Error Occured ${f.getMessage}")
    println(f.getStackTraceString)
    List()
  }
}

"select count(*) from ad_advisor_search where user_agent not in (" +
botlists.map(b => s"'${b}'").mkString(",") + ");"