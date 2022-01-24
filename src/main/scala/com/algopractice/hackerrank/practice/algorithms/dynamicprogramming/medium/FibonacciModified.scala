package com.hackerrank.practice.algorithms.dynamicprogramming.medium

import java.util.Scanner
import scala.collection.mutable

/**
  * Created by sunitc on 10/30/17.
  * https://www.hackerrank.com/challenges/fibonacci-modified/problem
  */

/*
Input -> 0 1 5
Output -> 5

Input -> 0 1 10
Output -> 84266613096281243382112
 */
object FibonacciModified {

  def main(args: Array[String]): Unit = {

    val sc: Scanner = new Scanner(System.in)

    val t1: Int = sc.nextInt
    val t2: Int = sc.nextInt
    val n: Int = sc.nextInt

    val map: scala.collection.mutable.Map[Int, BigInt] = new mutable.HashMap[Int, BigInt]

    map.put(1, t1)
    map.put(2, t2)

    calculateFibonacci(n, map)
  }

  def calculateFibonacci(n: Int, map: mutable.Map[Int, BigInt]): Unit = {
    if(n > 2) {
      (3 to n) foreach(i => {

        val result: BigInt = map(i-2) + map(i-1)*map(i-1)
        map.put(i, result)

      })
    }
//    println(map)
    println(map.getOrElse(n, 0)
    )
  }
}
