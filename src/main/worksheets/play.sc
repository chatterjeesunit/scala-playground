import scala.util.Random

val fn: Int => Int = x => {
  val temp = new Random().nextInt(x)
  println(s"Generated random value $temp")
  temp
}

def someFn(num: => Int): Int = {
  lazy val tempVal = num  //Evaluates the value
  tempVal * tempVal * tempVal
}

println(s"Result = ${someFn(fn(100))}")





