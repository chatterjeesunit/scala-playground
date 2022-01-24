package com.hackerrank.challenges.codeweek35

import java.util.Scanner

/**
  * Created by sunitc on 11/16/17.
  */

/*
Sample Input
4 5
1 2 3 -1 -2
-5 -8 -1 2 -150
1 2 3 -250 100
1 1 1 1 20
Output => 37


Input
5 5
100 -10 -10 -100 -250
200 -10 -10 -10 -200
10 -10 -200 -10 -10
10 10 -200 -10 200
-10 -10 -200 -10 200

Answer => 650?


Input
5 5
12 23 -10 9 -4
3 -9 7 2 1
-1 5 2 18 6
0 -32 0 61 0
3 1 -6 -6 -9

Answer => 134
 */
object MatrixLand {

  object ParentCell extends Enumeration {
    type ParentCell = Value
    val TOP, LEFT, RIGHT, START = Value
  }

  case class Cell(result: Int, parent: ParentCell.Value)  {
    override def toString: String = s"($result, $parent)"
  }

  def printArray(arr: Array[Array[Int]]): Unit = {
    println(arr.map(_.mkString(",\t")).mkString("\n"))
  }

  def printArray(arr: Array[Array[Cell]]): Unit = {
    println(arr.map(_.mkString(",\t")).mkString("\n"))
  }


  def main(args: Array[String]): Unit = {
    val sc: Scanner = new Scanner(System.in)

    val n: Int = sc.nextInt()
    val m: Int = sc.nextInt()
    val input = Array.ofDim[Int](n,m)
    val result = Array.ofDim[Cell](n,m)

    (0 to n-1) foreach(i => {
      (0 to m-1) foreach(j => {
        input(i)(j) = sc.nextInt()
      })
    })

    //    printArray(input)

    (0 to n-1) foreach(i => {
      (0 to m - 1) foreach (j => {
        result(i)(j) = (i, j) match {
          case (0, 0) => Cell(input(i)(j), ParentCell.START)
          case (0, _) => Cell(input(i)(j) + result(i)(j - 1).result, ParentCell.LEFT)
          case (_, 0) => Cell(input(i)(j) + result(i - 1)(j).result, ParentCell.TOP)
          case _ => {
            val maxTop: Int = result(i - 1)(j).result
            val maxLeft: Int = result(i)(j - 1).result
            if (maxTop > maxLeft) Cell(maxTop + input(i)(j), ParentCell.TOP)
            else Cell(maxLeft + input(i)(j), ParentCell.LEFT)
          }
        }
      })
      (m - 1 to 1 by -1) foreach (j => {
        val rij: Cell = result(i)(j)

        rij.parent match {
          case ParentCell.LEFT | ParentCell.START => {
            if (result(i)(j - 1).result < rij.result)
              result(i)(j - 1) = Cell(rij.result, result(i)(j - 1).parent)
          }
          case ParentCell.TOP | ParentCell.RIGHT  => {
            val temp: Int = rij.result + input(i)(j-1)
            if(temp > result(i)(j-1).result) {
              result(i)(j - 1) = Cell(temp, ParentCell.RIGHT)
            }
            if(temp > result(i)(j).result) {
              result(i)(j) = Cell(temp, ParentCell.RIGHT)
            }
          }
          case _ => // Do Nothing
        }

      })
    })

        printArray(result)

    var maxVal: Int = result(n-1).map(_.result).max

    println(maxVal)
  }


}
