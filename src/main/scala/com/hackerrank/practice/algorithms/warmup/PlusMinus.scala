package com.hackerrank.practice.algorithms.warmup

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object PlusMinus {

  def main(args: Array[String]): Unit ={

    val numbers: List[Double] = Try{
      val n:Double = scala.io.StdIn.readLine().toDouble

      val numbers: List[Int] = scala.io.StdIn.readLine().split(" ").toList.map(_.toInt)

      val (greaterThanZero: List[Int], negative: List[Int]) = numbers.partition(_ >= 0)

      val (zero: List[Int], positive: List[Int]) = greaterThanZero.partition(_ == 0)

      List(positive.size/n, negative.size/n, zero.size/n)

    } match {
      case Success(res) => res
      case Failure(f)=> List(0.0,0.0,0.0)
    }

    numbers.map(d => println("%.6f".format(d)))
  }

}
