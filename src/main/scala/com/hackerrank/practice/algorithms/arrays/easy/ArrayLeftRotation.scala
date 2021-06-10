package com.hackerrank.practice.algorithms.arrays.easy

/**
  * Created by sunitc on 10/25/17.
  *
  * https://www.hackerrank.com/challenges/ctci-array-left-rotation
  */
/*
Sample Input
5 4
1 2 3 4 5
*/
object ArrayLeftRotation {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt()
    val k = sc.nextInt()
    var a = new Array[Int](n)
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt()
    }

    val d: Int = k % n

    val o1: Array[Int] = fastRotation(a, d, n)
    println(o1.mkString(" "))

    val o2: Array[Int] = inPlaceRotation(a, d, n)
    println(o2.mkString(" "))

    println("After inplace rotation.. original array is changed")
    println(a.mkString(" "))

  }


  def inPlaceRotation(a: Array[Int], d: Int, n: Int) : Array[Int] = {
    (1 to d )foreach(i => {
      val first: Int = a(0)
      (0 until n-1).foreach(i => {
        a.update(i, a(i+1))
      })
      a.update(n-1, first)
    })

    a
  }

  def fastRotation(a: Array[Int], d: Int, n: Int) : Array[Int] = {
    val b: Array[Int] = new Array[Int](n)


    for(i <- 0 to n-1) {
      val j = (i+d)%n
      b.update(i, a(j))
    }

    b
  }
}
