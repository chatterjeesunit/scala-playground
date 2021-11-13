package com.hackerrank.practice.algorithms.sorting

/**
  * Created by sunitc on 12/29/16.
  */
import java.util.Scanner

object InsertionSortPart2 {

  def main(args: Array[String]):Unit = {

    val sc: Scanner = new Scanner(System.in)
    val size: Int = sc.nextLine.trim.split(" ").toList.head.toInt
    val array: Array[Int] = sc.nextLine.trim.split(" ").map(_.toInt)

    def insert(array: Array[Int], index: Int, unsortedNum: Int): Array[Int] = {
      if(index > 0) {
        val elem:Int = array(index -1 )
        if(unsortedNum > elem) {
          array.update(index, unsortedNum)
          array
        }
        else {
          array.update(index, elem)
          insert(array, index-1, unsortedNum)
        }
      } else {
        array.update(index, unsortedNum)
        array
      }
    }

    def printArray(array: Array[Int]) : Unit = println(array.toList.mkString(" "))

    def sort(array: Array[Int], index: Int): Array[Int] = {
      if(size == 0) {
        printArray(array)
        array
      }
      else if(index == size -1) {
        val sortedArray: Array[Int] = insert(array, index, array(index))
        printArray(sortedArray)
        sortedArray
      }
      else {
        val sortedArray: Array[Int] = insert(array, index, array(index))
        printArray(sortedArray)
        sort(sortedArray, index+1)
      }
    }

    sort(array, 1)
  }
}