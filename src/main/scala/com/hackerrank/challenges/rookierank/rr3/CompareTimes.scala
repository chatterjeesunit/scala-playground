package com.hackerrank.challenges.rookierank.rr3

import scala.util.matching.Regex

/**
  * Created by sunitc on 5/6/17.
  * https://www.hackerrank.com/contests/rookierank-3/challenges/comparing-times
  */
/*
Test Cases
4
10:19PM 02:49AM
08:49AM 09:10AM
12:05AM 01:05AM
12:15PM 1:15PM

Results
Second
First
First
First
 */
object CompareTimes {

  val AmRegex: Regex = """([\d]{1,2}):([\d]{1,2})(AM|am)""".r
  val PmRegex: Regex = """([\d]{1,2}):([\d]{1,2})(PM|pm)""".r

  case class Time(minutes: Int) {
    def this(date: String) = {
      this(
        date match {
          case AmRegex(hr, min, _*) => {
//            println(s"hr = ${hr.toInt} ; min = ${min.toInt}")
            hr.toInt%12 * 60 + min.toInt
          }
          case PmRegex(hr, min, _*) => {
//            println(s"hr = ${hr.toInt  +12} ; min = ${min.toInt}")
            (hr.toInt%12 + 12) * 60 + min.toInt
          }
          case _ => throw new IllegalArgumentException ("Invalid date")
        })
    }
  }


  def main (args : Array[String]) = {
    val sc = new java.util.Scanner (System.in);
    val q = sc.nextInt();
    for(i <- 0 to q-1) {
      val t1 = sc.next()
      val t2 = sc.next()
      compareTimes(t1, t2)
    }
  }

  def compareTimes(first: String, second: String) = {
    if(new Time(first).minutes < new Time(second).minutes)
      println("First")
    else
      println("Second")
  }
}

