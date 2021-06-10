
val p1 = new Polynomial(Map((0, 5), (5,2), (3,6)))

val p2 = new Polynomial(Map((0->7), (2->3), (3->7)))

p1 + p2
p2+p1
case class Polynomial (terms:Map[Int,Int]) {

  override def toString =
    terms.
      toSeq.
      sortWith((a,b) => a._1 < b._1).
      map{case (a,b) => a match {
        case 0 => s"${b}"
        case _ => s"${b}x^${a}"
      }}.
      mkString(" + ")


  def + (other:Polynomial):Polynomial = {
    new Polynomial(
      this
        .terms
        .foldLeft(other.terms)((map,tup) =>
          map+ (tup._1 -> (tup._2 + map.getOrElse(tup._1, 0)))))
  }

  
}