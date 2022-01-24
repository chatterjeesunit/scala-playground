package com.hackerrank.practice.algorithms.dynamicprogramming.medium

import java.util.Scanner

/**
  * Created by sunitc on 12/29/16.
  * https://www.hackerrank.com/challenges/equal/problem
  */
object Equal {

  def main(args: Array[String]): Unit = {

    val sc: Scanner = new Scanner(System.in)
    val testCases: Int = sc.nextInt
    (0 until testCases).foreach(t => {
      val size: Int = sc.nextInt
      val array: Array[Int] = new Array[Int](size)
      (0 until size) foreach(i => array.update(i, sc.nextInt()))

      println(equal(array, size))
    })
  }

  def equal(array: Array[Int], size: Int): Int = {
    val min:Int = array.min
    (0 until size) foreach(i => array.update(i, array(i) - min))

    var count: Int = 0

    (0 until size) foreach(i => {
      while(array(i) > 0) {
        val currentDiff: Int = array(i)
        val num: Int = nextNum(currentDiff)
        array.update(i, currentDiff - num)
        count += 1
      }
    })
    count
  }

  def nextNum(num: Int): Int = {
    num match {
      case 1 => 1
      case 2 => 2
      case 5 => 5
      case i if i < 5 => 2
      case _ => 5
    }
  }

}


/*
4
2 2 3 7  -> 2

3
1 5 5 -> 3

 */