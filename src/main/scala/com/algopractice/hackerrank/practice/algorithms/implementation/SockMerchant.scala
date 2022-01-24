package com.hackerrank.practice.algorithms.implementation

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object SockMerchant {

  def main(args: Array[String]): Unit ={

    val n:Int = Try { scala.io.StdIn.readLine.toInt} match {
      case Success(num) => {
        Try{ scala.io.StdIn.readLine.split(" ").toList.map(_.toInt).take(num).groupBy(n => n).mapValues(_.size/2).values.reduceLeft(_ + _)}
        match {
          case Success(res) => res
          case Failure(f) => 0
        }
      }
      case Failure(f) => 0
    }

    println(n)
  }

}
