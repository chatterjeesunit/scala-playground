package com.hackerrank.practice.algorithms.implementation

/**
  * Created by sunitc on 6/15/17.
  *
  */
object SequenceEquation {

  case class Seq(y: Int, ppy: Int) {
    override def toString: String = ppy.toString
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    var len = sc.nextInt()
    var py: Array[Int] = new Array[Int](len) //Array of Int
    var ppySeq: Array[Seq] = new Array[Seq](len) //Array of Object

    //Read Input and create p(y)
    (1 to len).foreach(i => py.update(i - 1, sc.nextInt())) //Only n stores to array

    //Scan through array and create p(p(y))
    (1 to len).foreach(i => ppySeq.update(i - 1, Seq(i, py(py(i - 1)-1)))) //n stores to array  + 2n array access


    ppySeq.sortBy(_.ppy).foreach(ppy => println(ppy.y)) // nLogN access for sorting

   }
}

/*
Sample Input
5
5
1
2
3
4

 */