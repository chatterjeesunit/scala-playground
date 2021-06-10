def mergeSort[T](inputList: List[T])(implicit ord: Ordering[T]): List[T] = {

  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x::xs1, y::ys1) =>
      if(ord.lt(x,y))
        x:: merge(xs1, ys)
      else
        y:: merge(xs, ys1)
  }

  if(inputList.size <=1 ) inputList
  else {
    val n: Int = inputList.size/2
    val (first, second) = inputList.splitAt(n)

    merge(mergeSort(first), mergeSort(second))
  }

}

val l1: List[Int] = List (-8, 7, 3, 1, 2, 4, 9, 5, -2)
mergeSort(l1) // List(-8, -2, 1, 2, 3, 4, 5, 7, 9)
//Automatically works because Ordering[Int] is found in companion object of Ordering


val l2: List[String] = List("banana", "pineapple", "mango", "grapes")
mergeSort(l2) //List(banana, grapes, mango, pineapple)

case class Person(name: String, dept: String) {
  override def toString: String = s"${name}@${dept}"
}
object Person {

  implicit val ord:Ordering[Person] = new Ordering[Person] {
    override def compare(x: Person, y: Person): Int =
      x.dept.compareTo(y.dept) match {
        case n if n != 0 => n
        case _ => x.name.compareTo(y.name)
      }
  }
}

val l3: List[Person] = List(
  Person("John", "Sales"),
  Person("Susan", "Marketing"),
  Person("Andrew", "Sales"),
  Person("Angelina", "Marketing")
)
//lList(John@Sales, Susan@Marketing, Andrew@Sales, Angelina@Marketing)


mergeSort(l3)
// List(Angelina@Marketing, Susan@Marketing, Andrew@Sales, John@Sales)