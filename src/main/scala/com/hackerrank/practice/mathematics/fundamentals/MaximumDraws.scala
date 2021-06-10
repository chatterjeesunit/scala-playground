package com.hackerrank.practice.mathematics.fundamentals

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object MaximumDraws {

  def main(args: Array[String]): Unit = {

    val n: Int = Try { scala.io.StdIn.readLine().toInt} match {
      case Success(num) => num
      case Failure(f) => 0
    }

    (0 until n).foreach { i =>
      Try {
        scala.io.StdIn.readLine().toInt
      } match {
        case Success(number) => if(number == 0) println(0) else println((number + 1))
        case Failure(f) => println(0)
      }
    }
  }
}
