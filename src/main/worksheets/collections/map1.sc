


val zipMap = Map(
  (11, List(11, 21, 31, 41)),
  (45, List(33, 35 , 31, 41, 45)),
  (67, List(67, 61, 45, 51, 21)),
  (34, List( 32, 34, 51, 45, 31)))

zipMap.groupBy(_._1)

val v1 = zipMap
  .par
  .map(m => m._2.map(z => (z -> m._1)))
  .toList

  v1.flatten
  .groupBy(k => k._1)
    .map(k => (k._1 -> (k._1::k._2.map(i => i._2)) ))
  .mkString("\n")