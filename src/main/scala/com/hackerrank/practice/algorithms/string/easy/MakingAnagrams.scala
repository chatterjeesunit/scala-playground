package com.hackerrank.practice.algorithms.string.easy

import java.util.Scanner

/**
  * Created by sunitc on 10/26/17.
  * https://www.hackerrank.com/challenges/ctci-making-anagrams
  */
object MakingAnagrams {

  val asciiValForLowerCaseA: Int = 'a'.toInt

  def main(args: Array[String]): Unit = {
    val sc: Scanner = new Scanner(System.in)

    val stringOne: String = sc.next()
    val stringTwo: String = sc.next()

    val stringOneArray: Array[Int] = initializeAndReturnArray()
    val stringTwoArray: Array[Int] = initializeAndReturnArray()

    stringOne.foreach(c => updateArrayPosition(stringOneArray, c))
    stringTwo.foreach(c => updateArrayPosition(stringTwoArray, c))

    val result: Int = (0 until 26).map(i => Math.abs(stringOneArray(i) - stringTwoArray(i))).sum

    println(result)

  }

  def initializeAndReturnArray(): Array[Int] = {
    Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
  }


  def updateArrayPosition(array: Array[Int], char: Char): Unit = {
    val position: Int = char.toInt - asciiValForLowerCaseA
    array.update(position, array(position) + 1)
  }
}
