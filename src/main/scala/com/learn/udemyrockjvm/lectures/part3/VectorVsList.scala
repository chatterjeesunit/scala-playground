package com.learn.udemyrockjvm.lectures.part3

import scala.util.Random

object VectorVsList extends App {

  val maxCapacity = 1000000

  def benchmarkUpdateTimes(seq: Seq[Int], numOfUpdates: Int): Double = {
    var random = new Random()

    val times: Seq[Long] = for (n <- 1 to numOfUpdates)
    yield {
      val startTime = System.nanoTime();
      seq.updated(random.nextInt(maxCapacity), -1)
      val endTime = System.nanoTime()
      endTime - startTime
    }

    times.sum * 1.0 / numOfUpdates
  }

  val vector: Vector[Int] = (1 to maxCapacity).toVector
  val list: List[Int] = (1 to maxCapacity).toList


  val vectorAvgTime = benchmarkUpdateTimes(vector, 10000)
  val listAvgTime = benchmarkUpdateTimes(list, 10000)

  println(s"Average time for random updates in Vector of $maxCapacity size = $vectorAvgTime ns")
  println(s"Average time for random updates in List of $maxCapacity size = $listAvgTime ns")
}
