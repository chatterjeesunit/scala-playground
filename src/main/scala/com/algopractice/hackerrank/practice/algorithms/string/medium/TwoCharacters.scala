package com.hackerrank.practice.algorithms.string.medium

/**
  * Created by sunitc on 12/25/16.
  */
object TwoCharacters {

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner(System.in);
    var len = sc.nextInt();
    var s = sc.next();
    val input: String = "beabefbtebfeabtareafabatar"

    def getDistinctChars(str: String): String = str.toSet.mkString
    def getRegex(input: String): String =
      s"(((${input})+${input.head}?)|((${input.reverse})+${input.tail.head}?)){1}"

    def isValid(str: String): Boolean = {
      val distinctChars:String = getDistinctChars(str)
      if(distinctChars.size == 2) str.matches(getRegex(distinctChars))
      else false
    }

    def getAllCombinations(input: List[Char], length: Int, acc: List[String]): List[String] = {
      if(length < 0 ) acc
      else if (length == 0) input.mkString::acc
      else if(length == 1) input.map(_.toString)
      else if(input.length == length) input.mkString::acc
      else {
        getAllCombinations(input.tail, length-1, acc).map(c => s"${input.head}$c") ++
          getAllCombinations(input.tail, length, acc)
      }
    }

    def getValidCombinations(input:String, minSize: Int): List[String] = {

      val distinctChars: String = getDistinctChars(input)

      getAllCombinations(distinctChars.toList, distinctChars.length - minSize, List()).map { s =>
        if(input.length > minSize) input.replaceAll(s"[$s]*","") else input
      }.filter(s => isValid(s))
    }

    val validCombinations: List[String] = getValidCombinations(s,2).sortWith((s1, s2) => s1.size > s2.size)

    System.out.println(validCombinations.headOption.map(s => s.size).getOrElse(0))
  }




}
