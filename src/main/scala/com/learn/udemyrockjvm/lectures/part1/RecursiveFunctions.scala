package com.learn.udemyrockjvm.lectures.part1

object RecursiveFunctions extends App {

  def sumN(n: Int): Int = {
    if (n <= 0) 0
    else n + sumN(n - 1)
  }

  println(s"Sum of first 10 numbers = ${sumN(10)}")
  println(s"Sum of first 1000 numbers = ${sumN(1000)}")


}
