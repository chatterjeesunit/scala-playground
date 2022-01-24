package com.hackerrank.practice.algorithms.sorting

/**
  * Created by sunitc on 12/29/16.
  */
import java.util.Scanner

object InsertionSortPart1 {

  def main(args: Array[String]):Unit = {

    val sc: Scanner = new Scanner(System.in)
    val size: Int = sc.nextLine.trim.split(" ").toList.head.toInt
    val array: Array[Int] = sc.nextLine.trim.split(" ").map(_.toInt)

    val unsortedNum: Int = array(size-1)

    def insert(array: Array[Int], index: Int): Array[Int] = {
      if(index > 0) {
        val elem:Int = array(index -1 )
        if(unsortedNum > elem) {
          array.update(index, unsortedNum)
          println(array.toList.mkString(" "))
          array
        }
        else {
          array.update(index, elem)
          println(array.toList.mkString(" "))
          insert(array, index-1)
        }
      } else {
        array.update(index, unsortedNum)
        println(array.toList.mkString(" "))
        array
      }


    }

    insert(array, size-1)
  }
}