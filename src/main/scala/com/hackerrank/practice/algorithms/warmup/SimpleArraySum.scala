package com.hackerrank.practice.algorithms.warmup

/**
  * Created by sunitc on 12/25/16.
  */
object SimpleArraySum {
  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner(System.in)

    val length = sc.nextInt

    def readAndSumInput(length: Int, acc: List[Int]): Int = {
      if(length == 0) acc.sum
      else readAndSumInput(length-1, sc.nextInt::acc)
    }

    println(readAndSumInput(length, List()))
  }
}
