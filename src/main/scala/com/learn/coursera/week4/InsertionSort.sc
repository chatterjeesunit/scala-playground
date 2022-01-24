def insertionSort(xs:List[Int]):List[Int] = xs match {
  case List() => xs
  case x::List() => xs
  case x::xs => insert(x, insertionSort(xs))
}

def insert(x:Int, xs:List[Int]):List[Int] = {
  xs match {
    case List() => List(x)
    case y::ys => if (x > y) y::insert(x, ys) else x::xs
  }
}

val list = List(9,12,3,7,19,34,15,2,3,5,8)
insertionSort(list)
