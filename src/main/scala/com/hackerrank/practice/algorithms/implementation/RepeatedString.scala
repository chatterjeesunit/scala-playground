package com.hackerrank.practice.algorithms.implementation

import scala.collection.mutable.ListBuffer

/**
  * Created by sunitc on 12/31/16.
  */
object RepeatedString {

  def main(args: Array[String]):Unit =  {
    val sc = new java.util.Scanner (System.in)
    var s = sc.next
    var n = sc.nextLong

    val list = new ListBuffer[Int]
    (s.length to 1 by -1 ).foreach( i => if(s(i-1) == 'a') list+=i)

    val aList = list.toList

//    println(aList.mkString(","))

    val r = n % s.size

//    println(s"r = $r")

    val remList = aList.filter(n => n <= r)

//    println(remList.mkString(","))
    println((n/s.size)*aList.size + remList.size)

  }
}
/*
badweaewwtbaae
10

aba
10

 */