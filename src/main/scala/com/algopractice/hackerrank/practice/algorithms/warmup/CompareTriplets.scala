package com.hackerrank.practice.algorithms.warmup

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/25/16.
  */
object CompareTriplets {
  def main(args: Array[String]) = {

    val scores: List[String] = scala.io.Source.stdin.getLines().take(2).toList

    val (aliceScore:Int, bobScore:Int) = Try {
      val aliceScores: List[Int] = scores.head.split(' ').toList.map(_.toInt)
      val bobScores: List[Int] = scores.tail.head.split(' ').toList.map(_.toInt)

      (0 until 3).map( i => {
        aliceScores(i).compareTo(bobScores(i)) match {
          case 1 => (1,0)
          case -1 => (0,1)
          case _ => (0,0)
        }
      }).toList.reduceLeft((a,b) => (a._1 + b._1, a._2 + b._2))
    } match {
      case Success(results) => results
      case Failure(f) => (0,0)
    }

    System.out.println(s"$aliceScore $bobScore")

  }

}
