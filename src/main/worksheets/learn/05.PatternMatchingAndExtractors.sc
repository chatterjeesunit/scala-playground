trait Types
case class TypeOne(x: String) extends Types
case class TypeTwo(x: String) extends Types
case class TypeThree(x: String, y: Int) extends Types
class TypeFour(val x: String) extends Types
class TypeFive(val x: String, val y:Int) extends Types

object TypeFour{
  def unapply(arg: TypeFour): Option[String] = Some(arg.x)
}

object FiveType {
  def unapply(arg: TypeFive): Option[(String, Int)] = Some((arg.x, arg.y))
}

val t1 = TypeOne("dummy")
val t2 = TypeTwo("dummy")
val t3 = TypeThree("dummy", 10)
val t4 = new TypeFour("dummy")
val t5 = new TypeFive("dummy", 20)

def performAction(input: Types) = input match {
  case TypeOne(x) => println(s"Type One found = ${x}")
  case TypeTwo(x) => println(s"Type Two found = ${x}")
  case TypeThree(x,y ) => println(s"Type Three found = ${x},  ${y}")
  case TypeFour(x) => println(s"Type Four found = ${x}")
  case FiveType(x,y ) => println(s"Type Five found = ${x},  ${y}")
  case _ => println("Invalid Type")
}

performAction(t1)
performAction(t2)
performAction(t3)
performAction(t4)
performAction(t5)

//Pattern matching and extractors using lists
def sumList(l: List[Int]):Int = l match {
  case List() => 0
  case ::(x, List()) => x
  case x::tail => x + sumList(tail)
}

sumList(List(1,2,3))
sumList(List(1))
sumList(List(0))

//Pattern matching and extractors using Streams
def sumStream(l: Stream[Int]):Int = l match {
  case x#::tail => x + sumStream(tail)
  case _ => 0
}

sumStream((5 to 8).toStream)
sumStream((1 to 1).toStream)
sumStream(Stream.empty)

//Use of extractors outside of pattern matching

val name1: String = "Sunit Chatterjee"
val name2: String = "John Paul Duminy"

object emailExt {
  def unapply(arg: String): Option[String] = {
    if(Option(arg).isDefined) {
      val names: Array[String] = arg.split(" ")
      names.size match {
        case 1 | 2 => Some(arg)
        case 3 => Some(s"${names(0)} ${names(2)}")
        case _ => None
      }
    }
    else None
  }

}


val greeting1: String = "Welcome " + emailExt.unapply(name1).getOrElse("!")
val greeting2: String = "Welcome " + emailExt.unapply(name2).getOrElse("!")