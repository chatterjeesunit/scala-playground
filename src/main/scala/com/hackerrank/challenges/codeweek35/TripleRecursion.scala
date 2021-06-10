package com.hackerrank.challenges.codeweek35

/**
  * Created by sunitc on 11/14/17.
  */
object TripleRecursion {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var m = sc.nextInt();
    var k = sc.nextInt();

    val a: Array[Array[Int]] = Array.ofDim[Int](n, n)

    (0 until n ) foreach (i => {
      (0 until n) foreach(j => {
        val newVal: Int = (i,j) match {
          case (0,0) => m
          case (0, _) => a(i)(j-1) - 1
          case (i, j) if (j < i) => a(i-1)(j) - 1
          case (i, j) if (i == j) => a(i-1)(j-1) + k
          case _ => a(i)(j-1) - 1
        }
        a(i)(j) = newVal
      })
    })

    println(a.map(ia => ia.mkString(" ")).mkString("\n"))

  }
}
