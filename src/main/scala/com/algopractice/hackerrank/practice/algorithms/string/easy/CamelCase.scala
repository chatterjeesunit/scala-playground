package com.hackerrank.practice.algorithms.string.easy

/**
  * Created by sunitc on 12/25/16.
  */
object CamelCase {

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner (System.in);
    val s = sc.next();

    def calculateWords(listOfChars: List[Char], count: Int): Int = {

      if(listOfChars.isEmpty) count
      else listOfChars.head.isUpper match {
        case true => calculateWords(listOfChars.tail, count + 1)
        case _ => calculateWords(listOfChars.tail, count)
      }
    }

    System.out.println(calculateWords(s.toList, 1))
  }

}
