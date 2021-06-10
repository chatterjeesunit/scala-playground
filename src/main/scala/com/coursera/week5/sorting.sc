case class Person(name:String, age:Int)
val list1 = List(2 , 3 ,5 ,6 ,7, 2, 1, 3, 8, 9, 5, 3, 0, 4, -1, 4, 8)
val list2 = List("banana", "apple", "pineapple", "oranges")
val list3 = List(Person("Sunit", 35), Person("Sonal", 31), Person("Avanti", 36), Person("Surya", 26))

insertsort(list1)
insertsort(list2)
mergesort(list1)
mergesort(list2)

implicit val ord = Ordering.by{ x:Person => x.age }
insertsort(list3)
mergesort(list3)


def mergesort[T](xs:List[T])(implicit ord:Ordering[T]) : List[T] = {
  def merge(xs:List[T], ys:List[T]):List[T] = (xs, ys) match {
    case (xs, List()) => xs
    case (List() , ys ) => ys
    case (x :: xs1, y::ys1) => if(ord.lt(x,y)) x :: merge (xs1, ys) else y :: merge (xs, ys1)
  }

  val midPos:Int = xs.length / 2
  if (midPos == 0) xs else  {
    val (l1, l2) = xs.splitAt(midPos)
    merge (mergesort(l1), mergesort(l2) )
  }
}

def insertsort[T](xs:List[T])(implicit ord:Ordering[T]):List[T] = {

  def insert[T](element:T, list:List[T])(implicit ord:Ordering[T]):List[T] = list match {
    case List() => List(element)
    case y :: ys => if( ord.gt(element, y) ) y :: insert(element, ys) else element :: list
  }

  xs match {
    case List() => List()
    case y :: ys => insert (y , insertsort(ys))
  }
}