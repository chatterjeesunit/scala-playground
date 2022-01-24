package com.hackerrank.practice.algorithms.implementation

/**
  * Created by sunitc on 6/22/17.
  */
/*
8 18
5 4
3 4
4 6
6 6
2 1
8 4
2 4
1 4
4 5
1 8
3 6
7 2
4 3
8 7
5 4
4 4
5 8
5 6
5 2

Answer 7
 */
object QueenAttackII {

  case class Line(var x1: Int, var y1: Int, var x2: Int, var y2: Int) {
    def numElement(): Int = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))

    override def toString: String = s"($x1,$y1) -> ($x2,$y2) : ${numElement()}"
  }

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner(System.in)
    var n = sc.nextInt()
    var k = sc.nextInt()
    var r = sc.nextInt() //Queen row
    var c = sc.nextInt() //Queen column

    //Diagonal D1
    val m1 = Math.min(n - r, c - 1)
    var d1: Line = Line(r + m1, c - m1, c - m1, r + m1)

    //Diagonal D2
    val m2: Int = Math.min(r - 1, c - 1)
    val d2: Line = Line(r - m2, c - m2, (n - (c - m2) + 1), (n - (r - m2) + 1))

    //Vertical
    val v: Line = Line(1, c, n, c)

    //Horizontal
    val h: Line = Line(r, 1, r, n)

    println("---BEFORE---")
    printLines(d1, d2, v, h)

    (1 to k) foreach (i => {
      var p = sc.nextInt()
      var q = sc.nextInt()
        //println("\n\n---AFTER--- ("+p+","+q+")----")
        if(p == r && q == c) {
          //Do Nothing
        }else if (p - r == c - q && p > r && d1.x1 >= p && d1.y1 <= q && d1.numElement() > 0) { // d1 top
          //println("d1-top")
          d1.x1 = p - 1
          d1.y1 = q + 1
        } else if (r - p == q - c && p < r && d1.x2 <= p && d1.y2 >= q && d1.numElement() > 0) { // d1 bottom
          //println("d1-bottom")
          d1.x2 = p + 1
          d1.y2 = q - 1
        } else if (r - p == c - q && p < r && d2.x1 <= p && d2.y1 <= q && d2.numElement() > 0) { // d2 bottom
          //println("d2-bottom")
          d2.x1 = p + 1
          d2.y1 = q + 1
        } else if (p - r == q - c && p > r && d2.x2 >= p && d2.y2 >= q && d2.numElement() > 0) { // d2 top
          //println("d2-top")
          d2.x2 = p - 1
          d2.y2 = q - 1
        } else if (q == c && p > r && v.x2 >= p && v.numElement() > 0) { //vertical top
          //println("v-top")
          v.x2 = p - 1
          v.y2 = q
        } else if (q == c && p < r && v.x1 <= p && v.numElement() > 0) { //vertical bottom
          //println("v-bottom")
          v.x1 = p + 1
          v.y1 = q
        } else if (r == p && q < c && h.y1 <= q && h.numElement() > 0) { //horizontal left
          //println("h-left")
          h.x1 = p
          h.y1 = q + 1
        } else if (r == p && q > c && h.y2 >= q && h.numElement() > 0) { //horizontal right
          //println("h-right")
          h.x2 = p
          h.y2 = q - 1
        }


//      printLines(d1, d2, v, h)
    })
    println("---FINALLY---")
    printLines(d1, d2, v, h)
    println(h.numElement() + v.numElement() + d1.numElement() + d2.numElement())


  }


  private def printLines(d1: Line, d2: Line, v: Line, h: Line) = {
    println("D1\t: " + d1)
    println("D2\t: " + d2)
    println("V\t: " + v)
    println("H\t: " + h)
  }
}
