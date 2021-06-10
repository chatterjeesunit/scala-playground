val res:List[Int] = List(1,4,7,4,2,3,4,1,5,2)

val result2 = 1::List(2,3)

val result3:List[Int] = List[Int]()

List(1,2):::List(3,4)

List(1,2)++List(3,4)

res.sorted.span(x => x < 4)

result2.reduceLeft(_ + _)
result2.reduceLeftOption(_ + _)
result2.foldLeft(10)(_ + _)


result2.reduceRight(_ + _)
result2.foldRight(10)(_ + _)


result3.foldLeft(0)(_ + _)
result3.reduceLeftOption(_ + _)
result3.reduceLeft(_ + _)
