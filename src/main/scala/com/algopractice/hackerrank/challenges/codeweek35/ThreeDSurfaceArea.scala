package com.hackerrank.challenges.codeweek35

/**
  * Created by sunitc on 11/15/17.
  */
object ThreeDSurfaceArea {

  def surfaceArea(A: Array[Array[Int]], H:Int, W:Int): Int =  {
    var totalArea:Int = 0
    (0 until H)foreach(i => {
      (0 until W)foreach(j => {
        val minusTop:Int = i match {
          case 0 => 0
          case _ => Math.min(A(i)(j), A(i-1)(j))
        }
        val minusLeft:Int = j match {
          case 0 => 0
          case _ => Math.min(A(i)(j), A(i)(j-1))
        }
        val minusBottom:Int = i match {
          case x if x == H-1 => 0
          case _ => Math.min(A(i)(j), A(i+1)(j))
        }
        val minusRight:Int = j match {
          case y if y == W-1 => 0
          case _ => Math.min(A(i)(j), A(i)(j+1))
        }

        val currentSum = (2+4*A(i)(j)) - minusTop - minusBottom - minusLeft - minusRight
        totalArea += currentSum
      })
    })
    totalArea
  }

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner (System.in)
    var H = sc.nextInt()
    var W = sc.nextInt()
    var A = Array.ofDim[Int](H,W)

    (0 until H)foreach(i => {
      (0 until W)foreach(j => {
        A(i)(j) = sc.nextInt()
      })
    })
    val result = surfaceArea(A, H, W);
    println(result)
  }


}
