package com.hackerrank.practice.mathematics.fundamentals

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object Handshake {

  def main(args: Array[String]): Unit = {

    val n:Int = Try {
      scala.io.StdIn.readLine.toInt
    } match {
      case Success(num) => num
      case Failure(f) => 0
    }

    def calculateHandShakes(num:Int, sum:Int):Int = {
      if(num <= 1) sum
      else calculateHandShakes(num-1 , sum + num-1)
    }

    (0 until n).foreach(i => {
      Try{
        calculateHandShakes(scala.io.StdIn.readLine.toInt, 0)
      } match {
        case Success(total) => println(total)
        case Failure(f) => println(0)
      }
    })
  }

}
