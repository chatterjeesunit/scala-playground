package com.hackerrank.practice.algorithms.warmup

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object SolveMeFirst {
  def main(args: Array[String]) {
    val inputList: List[String] = scala.io.Source.stdin.getLines().take(2).toList
    val sumResult: Int = inputList.size match {
      case 2 => Try {
        inputList.map(_.toInt).sum
      }match {
        case Success(result) => result
        case Failure(ex) => 0
      }
      case _ => 0
    }

    System.out.println(sumResult)
  }
}
