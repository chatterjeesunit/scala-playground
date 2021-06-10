package com.hackerrank.challenges.rookierank.rr3

/**
  * Created by sunitc on 5/6/17
  *
  * https://www.hackerrank.com/contests/rookierank-3/challenges/find-the-bug
  *
  */
object FindTheBug {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var grid = new Array[String](n);
    for(grid_i <- 0 to n-1) {
      grid(grid_i) = sc.next();
    }



    def getBugLocation(input: String): Option[Int] = {
      val index: Int = input.indexOf('X')
      if(index < 0 ) None else Some(index)
    }


    for(r <- 0 to n-1) {
      getBugLocation(grid(r)) match {
        case Some(c) => println(s"$r,$c")
        case _ => //Do Nothing
      }
    }
  }
}
