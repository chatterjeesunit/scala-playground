val l1 = List( "banana", "oranges", "pinapple", "mango", "grapes")
val l2 = List(1,2,3,4)
val l3 = List(5,6,7,8)
foldLeft(l1, 0)((x,y) => x.length + y)
foldRight(l1,0)((x,y) => x.length + y)
reduceLeft(l1)((x,y) => s"($x,$y)")

concat(l2, l3)
sum(l3)
product(l3)
reverse(l3)
map(l3, (x:Int) => x*2)
map(l1, (x:String) => (x, length(x.toList)))


def reduceLeft[T](xs:List[T])(op:(T,T) => T):T = xs match {
  case List() => throw new NoSuchElementException("ReduceLeft of an empty list")
  case y :: ys => foldLeft(ys,y)(op)
}


def foldLeft[T,U](xs:List[T], acc:U)(op:(T,U)=> U):U = xs match {
  case List() => acc
  case y :: ys => foldLeft(ys, op(y, acc))(op)
}


def reduceRight[T](xs:List[T])(op:(T,T) => T):T = xs match {
  case List() => throw new NoSuchElementException("Reduce Right on empty list")
  case List(x) => x
  case y :: ys => op(y, reduceRight(ys)(op))
}

def foldRight[T,U](xs:List[T], acc:U)(op:(T,U) => U):U = xs match {
  case List() => acc
  case y :: ys => op(y, foldRight(ys, acc)(op))
}

def concat[T](xs:List[T], ys:List[T]) = foldRight(xs , ys)(_ :: _)

def length[T](xs:List[T]):Int = foldLeft(xs, 0)((x,y) => 1 + y)
def sum(xs:List[Int]):Int = foldLeft(xs, 0)(_ + _)
def product(xs:List[Int]):Int = foldLeft(xs, 1)(_*_)
def reverse[T](xs:List[T]):List[T] = foldLeft(xs, List[T]())(_ :: _)
def map[T,U](xs:List[T], f:T => U):List[U] = foldRight(xs, List[U]())(f(_) :: _)