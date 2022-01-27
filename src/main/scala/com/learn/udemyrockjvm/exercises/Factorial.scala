package com.learn.udemyrockjvm.exercises

import scala.annotation.tailrec

object Factorial extends App {

  def factorial(n: Long): Long = {

    @tailrec
    def factRecursive(num:Long, result: Long): Long =
      if(num <= 0) result else factRecursive(num-1, result*num)

    factRecursive(n, 1)
  }

  println(s"Factorial of 1 = ${factorial(1)}")
  println(s"Factorial of 5 = ${factorial(5)}")
  println(s"Factorial of 10 = ${factorial(10)}")
  println(s"Factorial of 20 = ${factorial(20)}")
}
