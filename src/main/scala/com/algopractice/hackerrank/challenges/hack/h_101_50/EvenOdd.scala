package com.hackerrank.challenges.hack.h_101_50

/**
  * Created by sunitc on 6/21/17.
  */
object EvenOdd {

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner(System.in);
    //  Return the minimum number of chocolates that need to be moved, or -1 if it's impossible.
    var q = sc.nextInt();
    var a0 = 0;
    while (a0 < q) {
      var n = sc.nextInt()
//      var a = new Array[Int](n)

      var evenOutOfOrder: Long = 0
      var oddOutOfOrder: Long = 0
      var evenOnes: Long = 0
      var amtToGive: Long = 0

      (0 to n - 1).foreach(i => {
        val num = sc.nextInt()
//        a(i) = num

        if (i % 2 == 0 && num % 2 != 0) {
          evenOutOfOrder += 1
          if (num == 1) evenOnes += 1
        }

        if (i % 2 != 0 && num % 2 == 0) oddOutOfOrder += 1

        if (num > 1 && i % 2 == 0) amtToGive += (num - 2)
        if (num > 1 && i % 2 != 0) amtToGive += (num - 1)
      })

//      println("")
//      println(s"\tEOO: $evenOutOfOrder , OOO: $oddOutOfOrder, EO: $evenOnes, ATG: $amtToGive")

      //First balance evenOnes with other even out of order nodes which are greater than 1
      val d1: Long = Math.min(evenOutOfOrder - evenOnes, evenOnes)
      evenOutOfOrder -= d1 * 2
      evenOnes -= d1
//      println(s"\tEOO: $evenOutOfOrder , OOO: $oddOutOfOrder, EO: $evenOnes, ATG: $amtToGive, d1: $d1")

      //Then balance remaining evenOnes with Odd nodes out of order
      val d2: Long = Math.min(oddOutOfOrder, evenOnes)
      oddOutOfOrder -= d2
      evenOutOfOrder -= d2
      evenOnes -= d2
//      println(s"\tEOO: $evenOutOfOrder , OOO: $oddOutOfOrder, EO: $evenOnes, ATG: $amtToGive, d1: $d1, d2 : $d2")


      //Third balance remaining out of order nodes with each other
      val d3: Long = (evenOutOfOrder - evenOnes + oddOutOfOrder)/2
      var remainingOutOfOrder = evenOutOfOrder + oddOutOfOrder - d3*2
      amtToGive -= d1 + d2 + d3
//      println(s"\tROO: $remainingOutOfOrder , ATG: $amtToGive, d1: $d1, d2 : $d2, d3: $d3")

      //Balance remaining with other nodes
      val d4: Long = Math.min(remainingOutOfOrder, amtToGive)
      remainingOutOfOrder -= d4 - d4%2
//      println(s"\tEOO: $evenOutOfOrder , OOO: $oddOutOfOrder, EO: $evenOnes, ATG: $amtToGive, d1: $d1, d2 : $d2, d3: $d3, d4: $d4")

      val result: Long = if (remainingOutOfOrder > 0) -1 else (d1 + d2 + d3 + d4)

      println(result)

      a0 += 1
    }
  }

}

/*
Sample Input
4
6
6 8 3 1 1 4
5
3 1 1 1 1
3
14 3 10
5
1 1 1 1 4

 */