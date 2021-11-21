package com.rockthejvm.scalabeginners.lectures

object CallByValueVsCallByName extends App{

  def timer1[R](func: => R): R = {
    val startTime = System.nanoTime()
    val result:R = func
    val endTime = System.nanoTime()
    println(s"Time taken to execute function is ${endTime - startTime} nanoseconds")
    result
  }


  def timer2[R](func: R): R = {
    val startTime = System.nanoTime()
    val result:R = func
    val endTime = System.nanoTime()
    println(s"Time taken to execute function is ${endTime - startTime} nanoseconds")
    result
  }


  def fibonnaci(n: Long): Long = if (n <= 2) 1 else fibonnaci(n-2) + fibonnaci(n-1)


  println(s"Fibonacci of 50 is ${timer1(fibonnaci(50))}")

  println(s"Fibonacci of 50 is ${timer2(fibonnaci(50))}")
}
