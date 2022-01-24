package com.hackerrank.practice.algorithms.implementation

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object MiniMaxSum {

  def main(args: Array[String]): Unit = {

    Try {
      val input: List[Long] = scala.io.StdIn.readLine.split(" ").toList.take(5).map(_.toLong)

      def findCombinations(numbers:List[Long], n: Int, combinations: List[List[Long]]) : List[List[Long]] = {
        if (n == 1) numbers.map(v => List(v))
        else if(numbers.size == n) numbers:: combinations
        else findCombinations(numbers.tail, n-1 , combinations).map(l => numbers.head :: l) ++
        findCombinations(numbers.tail, n, combinations)
      }

      val results: List[Long] = findCombinations(input, 4, List()).map(_.sum)
      List(results.min, results.max)

    }
    match {
      case Success(res) => println(res.mkString(" "))
      case Failure(f) => println("0 0")
    }

  }
}
