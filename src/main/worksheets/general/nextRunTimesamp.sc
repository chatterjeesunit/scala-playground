import java.sql.Timestamp
import com.wealthminder.webapi.data.Constants._
import org.joda.time.DateTime

val lastRun = new DateTime(Timestamp.valueOf("2016-06-10 22:20:00"))
lastRun.getHourOfDay
lastRun.getMinuteOfHour
val next = lastRun.plusHours(4)
next.getHourOfDay * 60
next.getMinuteOfHour
next.withHourOfDay(FIRST_WORKING_HOUR_UTC)
next.withHourOfDay(FIRST_WORKING_HOUR_UTC).plusHours(next.getHourOfDay)
next.withHourOfDay(FIRST_WORKING_HOUR_UTC).plusHours(next.getHourOfDay).plusMinutes(next.getMinuteOfHour)


def getNextTimestamp(lastRunAt:Timestamp, afterXHours:Int):Timestamp = {
  ​
  val SATURDAY_INDEX: Int = 6 // According to joda.time.DateTime
  val SUNDAY_INDEX: Int = 7 // According to joda.time.DateTime
  ​
  val lastRun = new DateTime(lastRunAt)
  val next = lastRun.plusHours(afterXHours)
  val dayOfTheWeek = next.getDayOfWeek
  ​
  def addHourToStartofBusiness(currentTime:DateTime, weekendBuffer:Int,  xHours:Int):DateTime = {
    currentTime
      .plusDays(weekendBuffer)
      .withHourOfDay(FIRST_WORKING_HOUR_UTC + xHours)
      .withMinuteOfHour(0)
      .withSecondOfMinute(0)
      .withMillisOfSecond(0)
  }
  ​
  ​
  val nextRun = lastRun.getDayOfWeek match {
    case SATURDAY_INDEX => addHourToStartofBusiness(next , 2, afterXHours)
    case SUNDAY_INDEX => addHourToStartofBusiness(next , 1, afterXHours)
    case day if lastRun.getHourOfDay < FIRST_WORKING_HOUR_UTC => addHourToStartofBusiness(next , 0, afterXHours)
    case day if next.getHourOfDay < FIRST_WORKING_HOUR_UTC => {
      val deltaHours:Int = (24 - LAST_WORKING_HOUR_UTC ) + next.getHourOfDay
      next.getDayOfWeek match {
        case SATURDAY_INDEX => addHourToStartofBusiness(next , 2, deltaHours).plusMinutes(next.getMinuteOfHour)
        case _ => addHourToStartofBusiness(next , 0, deltaHours).plusMinutes(next.getMinuteOfHour)
      }
    }
    case _ => next
  }
  ​
  (new Timestamp(nextRun.getMillis))
}
​
getNextTimestamp(Timestamp.valueOf("2016-06-06 00:00:00"),4)  // Monday before BH
getNextTimestamp(Timestamp.valueOf("2016-06-06 08:20:00"),4)  // Monday before BH ***
getNextTimestamp(Timestamp.valueOf("2016-06-06 14:35:00"),4)  // Monday  BH ***
getNextTimestamp(Timestamp.valueOf("2016-06-06 18:10:00"),4)  // Monday  BH
getNextTimestamp(Timestamp.valueOf("2016-06-06 20:45:00"),4)  // Monday  BH, but next run out of BH
getNextTimestamp(Timestamp.valueOf("2016-06-07 03:00:00"),4)  // Tuesday before BH
getNextTimestamp(Timestamp.valueOf("2016-06-10 18:23:00"),4)  // Friday BH
getNextTimestamp(Timestamp.valueOf("2016-06-10 22:20:00"),4)  // Friday BH,  but next run out of BH
getNextTimestamp(Timestamp.valueOf("2016-06-11 03:15:00"),4)  // Saturday
getNextTimestamp(Timestamp.valueOf("2016-06-12 08:15:00"),4)  // Sunday