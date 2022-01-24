package com.topcoder.practice.algorithms.binarytree

import java.util.Scanner

/**
  * Created by sunitc on 10/26/17.
  * https://community.topcoder.com/stat?c=problem_statement&pm=12697
  */

/*
Sample Input Output
0  -> 1
1 -> 1
2 -> 3
3 -> 5
5 -> 21
10 -> 683
60 -> 768614336404564651
 */
object MinCars {

  def main(args: Array[String]): Unit = {
    val sc: Scanner = new Scanner(System.in)

    val height = sc.nextInt()
    var numCars: Long = calculateMinCars(height)
    println(numCars)
  }

  private def calculateMinCars(height: Int) = {
    var numCars: Long = 0l;
    (height - 1 to 1 by -2) foreach (i => {
      numCars += Math.pow(2, i).toLong
    })
    numCars += 1
    numCars
  }
}
