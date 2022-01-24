package com.hackerrank.practice.algorithms.dynamicprogramming.medium

import java.io.{BufferedReader, InputStreamReader, StreamTokenizer}
import java.util.StringTokenizer

/**
  * Created by sunitc on 11/4/17.
  */

/*
Input
5
4 10 3 12 20 7
Output
1344
(A1 * A2) * ((A3 * A4) * A5)

Input
4
40 20 30 10 30
Output
26000
A1 * (A2 * A3) * A4


Input
4
10 20 30 40 30
Output
30000
((A1 * A2) * A3) * A4

Input
2
10 20 30
Output
6000
A1 * A2
 */
object MatrixMultiplication {

  def main(args: Array[String]): Unit = {

    val reader: BufferedReader = new BufferedReader(new InputStreamReader(System.in))
    val streamTokenizer: StreamTokenizer = new StreamTokenizer(reader)

    val n: Int = Integer.parseInt(reader.readLine().trim())
    val mArr: Array[Int] = new Array[Int](n + 1)
    val kArr: Array[String] = new Array[String](n + 1)

    val st: StringTokenizer = new StringTokenizer(reader.readLine())
    var counter:Int = 0
    while(st.hasMoreTokens) {
      mArr.update(counter, Integer.parseInt(st.nextToken()))
      counter += 1
    }
//    println(mArr.mkString(", "))
    if(n == 1)
      println(s"Total Multiplications needed = 0")
    else if(n == 2) {
      println(s"Total Multiplications needed = ${mArr.product}")
      println(s"Order of multiplication = A1 * A2")
    }
    else {
      (1 to n) foreach (i => kArr.update(i, s"A$i"))
      val mm: Array[Array[Int]] = Array.ofDim[Int](n + 1, n + 1)

      (0 until n) foreach (counter => {
        var i: Int = 1
        var j: Int = counter + i
        while (i <= n && j <= n) {
          if (i == j) mm(i)(j) = 0
          else {
            var minVal: Int = Integer.MAX_VALUE.toInt
            var minK: Int = 0
            (i until j) foreach (k => {
              val tempVal: Int = mm(i)(k) + mm(k + 1)(j) + mArr(i - 1) * mArr(k) * mArr(j)
              if (tempVal < minVal) {
                minK = k
                minVal = tempVal
              }
            })
            mm(i)(j) = minVal
            if (minK > 0) {
              if (minK - i >= 1) {
                if(kArr(i) != null && kArr(minK) != null) {
                  kArr.update(minK, s"(${kArr(i)} * ${kArr(minK)})")
                  kArr.update(i, null)
                }
              }
              if (j - (minK + 1) >= 1) {
                if(kArr(minK + 1) != null && kArr(j) != null) {
                  kArr.update(minK + 1, s"(${kArr(minK + 1)} * ${kArr(j)})")
                  kArr.update(j, null)
                }

              }
            }
          }
          i += 1
          j += 1
        }
      })

      //    println(mm.map(arr => arr.mkString("[",",\t\t","]")).mkString(",\n"))

      println(s"Total Multiplications needed = ${mm(1)(n)}")

      println(s"Order of multiplication = ${kArr.filterNot(k => k == null).mkString(" * ")}")
    }
  }

}
