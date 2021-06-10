import java.sql.Timestamp
import java.text.{DateFormat, SimpleDateFormat}
import java.util.Calendar

import com.wealthminder.webapi.Predef._
import org.joda.time.{DateTime, Days}

val currentTime:Timestamp = new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis())
//ADDING 5 hours to current time
var calendar:Calendar = Calendar.getInstance()
calendar.setTime(currentTime)
calendar.add(Calendar.HOUR, 5)
val nextRun:Timestamp = new Timestamp(calendar.getTime().getTime())

//RESETTING THE TIME PART
calendar.setTime(nextRun)
calendar.set(Calendar.HOUR, 0)
calendar.set(Calendar.MINUTE, 0)
calendar.set(Calendar.SECOND, 0)
calendar.set(Calendar.MILLISECOND, 0)
calendar.set(Calendar.AM_PM, Calendar.AM)
val nextRunDate:Timestamp = new Timestamp(calendar.getTime().getTime())


val fromDate:Timestamp = new Timestamp(new DateTime(getSqlRightNow()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).minusDays(50).getMillis)
val toDate: Timestamp = new Timestamp(new DateTime(getSqlRightNow()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis)

val days: Int = Days.daysBetween(new DateTime(fromDate), new DateTime(toDate)).getDays
val interval: Int = 7
for {
  r <- 0 to days by interval
}yield {

  val startDate: Timestamp = new Timestamp(new DateTime(fromDate).plusDays(r).getMillis)
  val endDate: Timestamp = new Timestamp(new DateTime(fromDate).plusDays(r+interval).getMillis)
  (startDate, endDate)
}