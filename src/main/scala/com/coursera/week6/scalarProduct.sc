val xs:List[Int] = List( 1, 3, 5)
val ys:List[Int] = List(2, 4, 6)

xs zip ys map { case (x,y) => x*y} sum

(for ( (x,y) <- xs zip ys)yield(x*y)) sum