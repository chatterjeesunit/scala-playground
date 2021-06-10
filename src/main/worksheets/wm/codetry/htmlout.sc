import java.lang.StringBuilder
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSetMetaData}

import org.apache.commons.lang.StringUtils

import scala.util.{Failure, Success, Try}

val driver = "com.mysql.jdbc.Driver"
val url = "jdbc:mysql://localhost:3306/wmdev"
val username = "wmdev"
val password = "wmdev"

def createHTMLOutputString(rs: java.sql.ResultSet):String = {
  var sb:StringBuilder = new StringBuilder("")
  sb.append("<P ALIGN='center'><TABLE BORDER=1>")
  val rsmd: ResultSetMetaData = rs.getMetaData()
  val columnCount:Int = rsmd.getColumnCount()
  // table header
  sb.append("<TR>")
  0 until columnCount  foreach ( i => sb.append("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>"))
  sb.append("</TR>")
  // the data
  while (rs.next()) {
    sb.append("<TR>")
    0 until columnCount  foreach ( i => sb.append("<TD>" + rs.getString(i + 1) + "</TD>"))
    sb.append("</TR>")
  }
  sb.append("</TABLE></P>")
  return sb.toString()
}

def getPreparedStatementOne(values:List[Long], sql:String, connection:Connection, publishedStatus:String): PreparedStatement = {
  val finalQuery: String = values.size match {
    case 1 => sql
    case _ => sql.replaceAll("ad_advisor_id[\\s]*in[\\s]*\\([\\s]*\\?[\\s]*\\)", s"ad_advisor_id in ( ${values.map(a => "?").mkString(",")} )")
  }
  println(finalQuery)
  val stmt = connection.prepareStatement(finalQuery)
  println(stmt.toString)
  println(values)
  var conditionIndex: Int = 1
  (1 to values.size) foreach { i =>
    println(s"$i : $conditionIndex => ${values(conditionIndex-1)}")
    stmt.setLong(conditionIndex, values(conditionIndex-1))
    conditionIndex = conditionIndex + 1
  }

  stmt.setString(conditionIndex, publishedStatus)
  conditionIndex = conditionIndex + 1
  stmt
}

val connectionOpt:Option[Connection] = Try {
  Class.forName(driver)
  DriverManager.getConnection(url, username, password)
} match {
  case Success(conn) => Some(conn)
  case Failure(f) => {
    f.printStackTrace()
    None
  }
}

connectionOpt.map { connection =>
  Try {
    val advisorIds:String = "143318,196398,25448"
    val publishedStatus:String = "PUBLISHED"
    val sql:String = "SELECT * from ad_advisor_info where ad_advisor_id in (? ) and published_status = ? "
    val values = advisorIds.split(",").map(_.toLong)

    val stmt: PreparedStatement = getPreparedStatementOne(values.toList, sql, connection, publishedStatus)
    println(stmt)
    val resultSet = stmt.executeQuery()
    println(resultSet)

    var sb:StringBuilder = new java.lang.StringBuilder("")
    println(createHTMLOutputString(resultSet))
    //  }
  } match {
    case Success(s) => s
    case Failure(f) => f.printStackTrace()
  }
}

connectionOpt.map(_.close())


val sql:String = "SELECT * from ad_advisor_info where and published_status = ? and ad_advisor_id in (? )"
val advisorIds:String = "143318,196398,25448"
val countValues:Int = advisorIds.split(",").size
val replaceString:String = Vector.fill(countValues)("?").mkString(",")
val index:Int = StringUtils.ordinalIndexOf(sql, "?", 2)
sql.substring(0, index) + replaceString + sql.substring(index + 1)
