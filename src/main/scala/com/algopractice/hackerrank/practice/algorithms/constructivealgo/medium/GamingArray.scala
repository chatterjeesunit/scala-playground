package com.hackerrank.practice.algorithms.constructivealgo.medium

import java.util.Scanner

/**
https://www.hackerrank.com/challenges/an-interesting-game-1/problem

Sample Input Test Case
4
5
5 2 6 3 4
2
3 1
8
3 1 2 5 7 8 5 6
12
17 82 4 5 88 3 22 90 23 56 87 99
  */
object GamingArray {

  def main(arr: Array[String]): Unit = {

    val sc: Scanner = new Scanner(System.in)

    val g: Int = sc.nextInt()

    (1 to g) foreach( i => {
      val n:Int = sc.nextInt()
      val array:Array[Int] = (1 to n).map(i => sc.nextInt()).toArray
      printWinner(array);
    })

  }


  def printWinner(array: Array[Int]):Unit = {
    var lastBigNumber : Int = 0
    var counter:Int = 0

    (0 until array.length) foreach( i => {
      if(i == 0) {
        lastBigNumber = array(i)
        counter = counter + 1
      }else {
        if(array(i) > lastBigNumber) {
          lastBigNumber = array(i)
          counter = counter + 1
        }
      }
    })
    if(counter % 2 == 0) println("ANDY") else println("BOB")
  }
}
