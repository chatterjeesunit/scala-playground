import java.util.NoSuchElementException

val list1 = List(1, 2, 3, 4, 5, 6)
val list2 = List(7, 8 , 9, 10, 11)
val bigList = List(List (3,4,5), List(2,3,1), 6,  List(9,4))
val fruits = List("apple", "oranges", "pineapple", "banana")
last(list1)
init(list1)
val list3:List[Int] = concat(list1, list2)
reverse(list3)
elementAt(list3, 7)
removeAt(list3, 7)
removeAt(list3, -1)
map[String, (String,Int)](fruits, s => (s,s.length), List())
filter[Int](list3, x => x%3==0)
flatten(bigList)
implicit val ord = Ordering.Char
encode("This is a very good example".toList)

def last[T](xs:List[T]):T = xs match {
  case List() => throw new NoSuchElementException("last of empty list")
  case x :: Nil => x
  case y :: ys => last(ys)
}

def init[T](xs:List[T]):List[T] = xs match {
  case List() => throw new NoSuchElementException("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

def concat[T](xs:List[T], ys:List[T]) : List[T] = xs match {
  case List() => ys
  case x::xs1 => x :: concat(xs1, ys)
}

def reverse[T](xs:List[T]):List[T] = xs match {
  case List() => List()
  case y :: ys => concat(reverse(ys), List(y))
}

def elementAt[T](xs:List[T], index:Int) : T = (xs, index) match {
  case (List(), _) => throw new IndexOutOfBoundsException("index bound error")
  case (y::ys, 0) => y
  case (y :: ys, _ )  => elementAt(ys, index - 1)
}

def removeAt[T](xs :List[T], index : Int) : List[T] = (xs, index) match {
  case (List(), _) => List()
  case (y::ys, 0) => ys
  case (y::ys, _) =>  y::removeAt(ys, index - 1)
}

def map[T,U](xs:List[T] , f:T => U, acc:List[U]):List[U] = xs match {
  case List() => acc
  case y :: ys => map(ys, f, acc ++ List(f(y)))
}

def filter[T](xs:List[T], p:T => Boolean):List[T] = xs match {
  case List() => List()
  case y :: ys => if(p(y)) y::filter(ys,p) else filter(ys,p)
}


def flatten(xs:List[Any]):List[Any] = xs match {
  case List() => List()
  case y :: ys => y match {
    case l:List[Any] => l ++ flatten(ys)
    case _ => y :: flatten(ys)
  }
}


def encode[T](xs:List[T])(implicit ord:Ordering[T]):List[(T, Int)] = {
  def pack(xs:List[T]) :List[List[T]] = xs match {
    case List() => List()
    case x :: xs1 => {
      val (l1, l2) = xs.span(y => y.equals(x))
      l1 :: pack(l2)
    }
  }

  pack(xs.sorted).map(x => (x.head, x.length))
}