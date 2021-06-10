package com.hackerrank.practice.algorithms.warmup

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object AVeryLargeSum {
  def main(args: Array[String]) {
    val lines: List[String] = scala.io.Source.stdin.getLines.take(2).toList
    Try{
      val n: Int = lines(0).toInt

      val numbers: List[Long] = lines(1).split(" ").toList.map(_.toLong)

      numbers.sum
    } match {
      case Success(result) => println(result)
      case Failure(f) => println(0)
    }
  }
}
