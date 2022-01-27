package com.learn.udemyrockjvm.exercises

import scala.annotation.tailrec

object Fibonnaci extends App {


  def fibonnaci(n: Long): Long = if (n <= 2) 1 else fibonnaci(n-2) + fibonnaci(n-1)


  def fibonacciTailRecursive(n: Long): Long = {
    if(n <= 2) 1
    else {
      @tailrec
      def fib(num: Long, n1: Long, n2: Long): Long = {
        if(num == n) n1 + n2
        else fib(num+1, n2, n1+n2)
      }

      fib(3, 1, 1 )
    }
  }


  (0 to 50 by 10).foreach(i => {
    val startTime = System.nanoTime()
    val result = fibonnaci(i)
    val endTime = System.nanoTime()
    println(s"Fibonnaci(${i}), Normal Recursion = ${result}, elapsed time = ${endTime - startTime} ns")
  })


  (0 to 50 by 10).foreach(i => {
    val startTime = System.nanoTime()
    val result = fibonacciTailRecursive(i)
    val endTime = System.nanoTime()
    println(s"Fibonacci (${i}), Tail Recursive = ${result}, elapsed time = ${endTime - startTime} ns")
  })

}
