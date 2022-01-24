
(queens(5) map show).mkString("\n")

def queens(n:Int):Set[List[Int]] = {

  def placeQueens(row:Int):Set[List[Int]] = {
    if(row < 0) Set(List())
    else {
      val res = for {
        queens <- placeQueens(row-1)
        column <- 0 to n-1
        if(isSafePosition(row, column, queens))
      }yield {
       column::queens
      }
      res
    }
  }


  def isSafePosition(r:Int, c:Int, pos:List[Int]):Boolean = {
    !pos.contains(c) &&
      (0 until pos.size)
        .zip(pos.reverse)
        .forall { case (x, y) => Math.abs(x - r) != Math.abs(y - c) }
  }

  placeQueens(n-1).map(_.reverse)
}

def show(res : List[Int]):String = "\n" + res.map ( r =>
    Vector.fill(res.size)("o").updated(r, "x").mkString
  ).mkString("\n")