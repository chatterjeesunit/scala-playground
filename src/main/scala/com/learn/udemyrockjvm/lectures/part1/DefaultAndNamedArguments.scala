package com.learn.udemyrockjvm.lectures.part1

import org.joda.time.DateTime

import scala.annotation.tailrec

object DefaultAndNamedArguments extends App {

  @tailrec
  def sumN(currentNum: Int, sum: Int = 0): Int = {
    if (currentNum <= 0) sum
    else sumN(currentNum - 1, sum + currentNum)
  }


  println(s"Sum of first 10 numbers = ${sumN(10)}")
  println(s"Sum of first 100 numbers = ${sumN(100)}")


  def logMessage(message: String, timestamp: DateTime = DateTime.now(), logLevel: String = "INFO"): Unit = {
    println(s"${logLevel} - ${timestamp} : ${message}")
  }

  logMessage("dummy message")

  logMessage("dummy message", logLevel = "DEBUG")


  logMessage(logLevel = "DEBUG", message = "dummy message")


  def displayMessage(greet: String = "Hello", name: String): Unit = {
    println(s"$greet $name")
  }

  displayMessage("Hola", "Sunit")
  displayMessage(name = "Sunit")
}
