import java.text.SimpleDateFormat

import com.wealthminder.marketplace.advisor.ClaimStatus
//import com.wealthminder.marketplace.util.MarketPlaceUtil
import com.wealthminder.webapi.data.DataUtility

import scala.util.matching.Regex
val toDate:String = "2015-10-10"
val parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDate.take(10))
//MarketPlaceUtil.splitname("Ida")
//MarketPlaceUtil.splitname("Sunit Chatterjee")
//MarketPlaceUtil.splitname("Santhi S. Swaroop")
//MarketPlaceUtil.splitname("Francis De La Cruz")

val str:Option[String] = None

str.map(s => ClaimStatus.withName(s)).getOrElse(ClaimStatus.PENDING)
val pageSize :Int = 10
val totalRecords:Int = 51

val pageCount:Int = (totalRecords.toDouble/pageSize).ceil.toInt

val advIds:List[Long] = List(132431,123412,123423,123412,456547,124312,534522
  ,127324,834123,234576,235423,345644)

advIds.size
advIds.mkString((",")).getBytes.size
4*1024/advIds.mkString((",")).getBytes.size*advIds.size

"DEV -> "+ "DEV".hashCode
"PROD -> "+ "PROD".hashCode
"STAGE -> "+ "STAGE".hashCode
"LOCAL -> "+ "LOCAL".hashCode
"TEST -> "+ "TEST".hashCode
val args:Array[String] = Array("aasd", "asdfa")
//Console.println(args.mkString(","))
def substr(str:String, len:Int) = {
  str.length match {
    case l if l < len => str
    case _ => str.substring(0, len)
  }
}
substr("34111-123" ,5)
substr("94065" ,5)
substr("940", 5)
"34111-123" take 5
"94065" take 5
"940" take 5
DataUtility.decryptValue("9CDE41F863188829,343F952AEA3CDC5E2BE88455720705C8")
DataUtility.decryptValue("D8EDD5B631B73EAB,8022FA0E0D01DFEF")
DataUtility.encryptValue("wmdev")
DataUtility.encryptValue("401k")
args.mkString(" ")
args.mkString("'", "' '", "'")
//DataUtility.decryptValue("3473B9DA12E2300AD94FC77E474083E4363C555F")
//val currSaltAndHash = "3473B9DA12E2300AD94FC77E474083E4363C555F,3B7233C65AE47B7091DBC6768BA0CCCD268D8612EFB159DB3E4FDF4829D2C5BB".split(",")
//DataUtility.hashString(DataUtility.convertFromHex(currSaltAndHash(0)), "welcome")
def sum(i:Double, acc:Double)(f:Double=>Double):Double = {
  if(i<0) acc else sum(i-1, acc + f(i))(f)
}

sum(100l,0l)(x => Math.pow(2,x))
sum(101l,0l)(x => Math.pow(2,x))
sum(102l,0l)(x => Math.pow(2,x))
sum(103l,0l)(x => Math.pow(2,x))