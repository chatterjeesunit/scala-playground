package com.learn.udemyrockjvm.lectures.part3

object CollectionsRanges extends App {

  // RANGES

  val rangeInclusive = 1 to 10
  rangeInclusive.foreach(x => println(x))

  val rangeExclusive = 1 until 10
  rangeInclusive.foreach(x => println(x))

  val rangeWithStep = 1 to (10,2)  // 1 3 5 7 9
  rangeWithStep.foreach(x => println(x))

  //Range can be converted to seq
  val seq: Seq[Int] = 1 until 10
  

}
