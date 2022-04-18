package com.learn.udemyrockjvm.lectures.part4afp

import scala.util.Random


object LazyVals extends App {

  lazy val x = {
    println("evaluating lazy val for first time")
    50
  }
  println("........")
  println(x + x + x)


  val fn: Int => Int = x => {
    val temp = new Random().nextInt(x)
    println(s"Generated random value $temp")
    temp
  }

  def someFn(num: => Int): Int = num * num * num

  println(s"Result = ${someFn(fn(100))}")

  def someFnAgain(num: => Int) = {
    lazy val tempVal = num  //Evaluates the value
    tempVal * tempVal * tempVal
  }

  println(s"Result = ${someFnAgain(fn(100))}")
}
