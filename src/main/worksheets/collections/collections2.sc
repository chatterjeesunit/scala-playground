val scores:List[Double] = List(3, 5.0, 2.8, 4, 3.5, 1.5, 3.5)
scores
scores.sorted.zipWithIndex
scores.sorted.zipWithIndex.groupBy(_._1).map(m => (m._1, m._2.head._2))


val l1 = List(1,2,3)++List(4)
val l2 = List(5,2,3):::List(1):::List(6):::List(7)
val l3 = 7:: 9 :: 3::1:: List(2,4)

l2.slice(0,l2.length - 2)

l2.drop(2)

l1 diff l3

l2 diff l3

l3 diff l2

List() diff l1
List() diff List()

l1(0)
l1(1)
l1(2)
l1(3)

val values = List (1,1,2,3,4,2,1,4,54,2,2,6,67,2,5,3)
values.groupBy(identity).mapValues(_.size)


val v1 = Vector(11, 22, 11, 33, 44, 66, 44, 55, 77, 44, 33, 22)
val v2 = Vector(11, 55, 99)
v2 intersect v1
