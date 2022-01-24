package com.hackerrank.practice.algorithms.bitmanipulation

import java.util.Scanner

import scala.collection.mutable.StringBuilder

/**
  * Created by sunitc on 12/26/16.
  */
object Cipher {

  def main(args: Array[String]): Unit = {

    val sc: Scanner = new Scanner(System.in)
    val n:Int = sc.nextInt()
    val k:Int = sc.nextInt
    val input: String = sc.next()


    // x = 0 => a(x)
    // x > 0 && x <= k-1 => a(x) || a(x-1)
    // x > k-1 && x < n => a(x) || a(x-1) || output(x-k+1)

//    def decode(index:Int , output:String): String = {
//      if(index == n) output
//      else if(index == 0) decode(index+1, output ++ input(index).toString)
//      else if (index > 0 && index <= k-1) decode(index+1, output ++ (input(index).getNumericValue ^ input(index-1).getNumericValue).toString)
//      else decode(index+1, output ++ (input(index).getNumericValue ^ input(index-1).getNumericValue ^ output(index-k).getNumericValue).toString)
//    }

    val output: StringBuilder = new StringBuilder("")
    (0 until n).foreach( i => {
      if(i == 0) output.append(input(i).toString)
      else if (i > 0 && i <= k-1) output.append((input(i).getNumericValue ^ input(i-1).getNumericValue).toString)
      else output.append((input(i).getNumericValue ^ input(i-1).getNumericValue ^ output(i-k).getNumericValue).toString)
    })

    println(output.toString)
  }


  /*
Sample Tests
Input - 1
7 4
1110100110
Output
1001010


Input - 2
6 2
1110001
Output
101111

Input - 3
8 3
1100000010
Output
10110110



Input - 4
8 4
11010110010
Output
10110110


   */
}
