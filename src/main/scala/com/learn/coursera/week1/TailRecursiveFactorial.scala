package com.coursera.week1

import scala.annotation.tailrec

/**
  * Created by sunitc on 29/5/16.
  */
object TailRecursiveFactorial {

  def factorial(x:Long):Long = fact(x, 1)

  @tailrec
  def fact(x:Long, acc:Long):Long = if(x == 1l) acc else fact(x-1, acc*x)


  def factOrig(x:Long):Long = if(x==1) x else x * factOrig(x-1l)
}
