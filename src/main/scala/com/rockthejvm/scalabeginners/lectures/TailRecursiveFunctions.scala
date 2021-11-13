package com.rockthejvm.scalabeginners.lectures

import scala.annotation.tailrec

object TailRecursiveFunctions extends App {


  def sumN(n: Int): Int = {

    @tailrec
    def recursiveSum(currentNum: Int, sum: Int): Int = {
      if (currentNum <= 0) sum
      else recursiveSum(currentNum - 1, sum + currentNum)
    }

    recursiveSum(n, 0)
  }

  println(s"Sum of first 10 numbers = ${sumN(10)}")
  println(s"Sum of first 1000 numbers = ${sumN(1000)}")
  println(s"Sum of first 100000 numbers = ${sumN(100000)}")

}
