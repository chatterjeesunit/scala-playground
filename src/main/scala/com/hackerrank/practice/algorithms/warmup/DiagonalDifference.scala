package com.hackerrank.practice.algorithms.warmup

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object DiagonalDifference {

  def main(args: Array[String]): Unit = {

    Try {
      val n: Int = scala.io.StdIn.readLine().toInt

      def diagSum(index:Int, sum: Int): Int = {

        if(index == n) Math.abs(sum)
        else {
          val input: List[Int] = scala.io.StdIn.readLine().split(" ").toList.map(_.toInt)

          diagSum(index+1, sum + input(index) - input(n-1-index))
        }
      }

      diagSum(0,0)
    } match {
      case Success(res) => println(res)
      case Failure(f) => println(0)
    }
  }

}
