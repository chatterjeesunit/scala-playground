package com.hackerrank.practice.algorithms.graphtheory.medium

/**
  * Created by sunitc on 1/1/17.
  * https://www.hackerrank.com/challenges/journey-to-the-moon/problem
  */

/*
10 7
0 2
1 8
1 4
2 8
2 6
3 5
6 9

ans 23
  */
import java.util.Scanner;

object JourneyToMoon {

  def main(args: Array[String]) {

    val sc: Scanner = new Scanner(System.in)
    val n: Int = sc.nextInt
    val i: Int = sc.nextInt

    val countryRoots: Array[Int] = new Array[Int](n)
    val countrySize: Array[Int] = new Array[Int](n)

    (0 until n) foreach(i => {
      countryRoots.update(i, i)
      countrySize.update(i, 1)
    })

    (0 until i).foreach(line => {
      val a: Int = sc.nextInt
      val b: Int = sc.nextInt
      connect(a, b, countryRoots, countrySize)
    })


//    println(countryRoots.mkString(","))
//    println(countrySize.mkString(","))

    val countryAstronautCounts = countrySize.filter(count => count > 0)
//    println(countryAstronautCounts.mkString(","))

    var astronautsCountedSoFar = 0
    var totalCombinations: Long = 0l
    (0 until countryAstronautCounts.length) foreach (i => {
      astronautsCountedSoFar += countryAstronautCounts(i)
      totalCombinations += countryAstronautCounts(i) * (n - astronautsCountedSoFar).toLong
    })

    println(totalCombinations)
  }

  def findRoot(i: Int, arr: Array[Int]): Int = {
    if(arr(i) == i) i else findRoot(arr(i), arr)
  }

  def connect(a:Int, b:Int, rootArray: Array[Int], sizeArray: Array[Int]): Unit = {
    val rootA: Int = findRoot(a, rootArray)
    val rootB: Int = findRoot(b, rootArray)

    if(rootA != rootB) {
      val sizeA:Int = sizeArray(rootA)
      val sizeB:Int = sizeArray(rootB)

      if(sizeA < sizeB) {
        rootArray.update(rootA, rootB)
        sizeArray.update(rootB, sizeA+sizeB)
        sizeArray.update(rootA, 0)
      }
      else {
        rootArray.update(rootB, rootA)
        sizeArray.update(rootA, sizeA+sizeB)
        sizeArray.update(rootB, 0)
      }
    }
  }
}