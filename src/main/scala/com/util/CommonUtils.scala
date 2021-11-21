package com.util

object CommonUtils {

  def timer[R](func: => R): R = {
    val startTime = System.nanoTime()
    val result:R = func
    val endTime = System.nanoTime()
    println(s"Time taken to execute function ${func.toString} is ${endTime - startTime} nanoseconds")
    result
  }

}
