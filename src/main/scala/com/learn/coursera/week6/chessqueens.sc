

def isPosSafe(row:Int, col:Int, positions:List[Int]):Boolean = {
  !positions.contains(col) &&
    (0 until positions.size).zip(positions.reverse)
      .forall{case (x,y) => Math.abs(row-x) != Math.abs(col - y)}
}


def getQueenPos(row:Int, n:Int, pos:List[Int], acc:Set[List[Int]]):Set[List[Int]] = {
  if (row == n) acc ++ Set[List[Int]](pos)
  else {
    val res = (0 until n) flatMap { c =>
      if (isPosSafe(row, c, pos)) {
        val newPos = c::pos
        getQueenPos(row + 1, n, newPos, acc)
      } else Set(List[Int]())
    }
    acc ++ res
  }

}

val results = getQueenPos(0, 4, List[Int](), Set[List[Int]]())
  .map(_.reverse)
  .filterNot(_.isEmpty)

results.map{ l =>
  val res = (0 until l.size).map(r => "o").toList
  (l.map{ colNum =>
    res.updated(colNum, "x").mkString("")
  }).mkString("\n","\n","\n")
}.mkString("-----")