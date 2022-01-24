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


Input
5 5
-10 -9 35 -4 20
-3 -9 -7 -2 -1
-1 -5 -2 -18 -6
0 -32 0 61 0
3 1 -6 -6 -9

Answer =>


Input
2 4
1 2 3 -8
5 2 -1 4

Answer => 16

Input
2 4
-100 -1 -10 -9
-20 -3 -4 10

Answer => 2
 */
object MatrixLand2 {

  case class Cell(result: Int, visitedCells: List[Int])  {
//    override def toString: String = s"($result, [${visitedCells.mkString(",")}])"
    override def toString: String = s"$result"


    def addTop(topCell: Cell, j:Int): Cell = {
      Cell(topCell.result + this.result, List(j))
    }

    def addLeftOrRight(otherCell: Cell, thisCellOriginalValue: Int, i:Int, j: Int): Cell = {
      if(otherCell.visitedCells.contains(j)) {
        if(otherCell.result > this.result) {
          otherCell.copy()
        }
        else
          this
      }
      else {
        val newResult: Int = thisCellOriginalValue + otherCell.result
        if(this.visitedCells.isEmpty) {
          if(i == 0) {
            if(this.result > newResult) this.copy(visitedCells = j :: visitedCells)
            else Cell(newResult, j::otherCell.visitedCells)
          }
          else Cell(newResult, j::otherCell.visitedCells)
        }
        else {
          if(this.result > newResult)  this
          else Cell(newResult, j::otherCell.visitedCells)
        }
      }
    }
  }

  def printArray(arr: Array[Array[Int]]): Unit = {
    println(arr.map(_.mkString(",\t")).mkString("\n"))
  }

  def printArray(arr: Array[Array[Cell]]): Unit = {
    println(arr.map(_.mkString(",\t")).mkString("\n"))
  }

  def printArray(arr: Array[Cell], i: Int): Unit = {
    (1 to i+1)foreach(i => print("\t"))
    println(arr.mkString(", "))
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


    def newCell(i:Int, j:Int): Cell = Cell(input(i)(j), List())

    def checkAndSetNewValue(i: Int, j: Int, jNew: Int): Boolean = {
      var isChanged: Boolean = false
      val newResult: Cell = result(i)(j).addLeftOrRight(result(i)(jNew), input(i)(j), i, j)
      if (newResult.result > result(i)(j).result) {
        isChanged = true
      }
      result(i)(j) = newResult
      isChanged
    }

    def goLeft(i: Int):Unit = {
      var isChanged: Boolean = false
      (m - 2 to 0 by -1) foreach (j => {
        isChanged = checkAndSetNewValue(i, j, j+1) || isChanged
      })
//      printArray(result(i), i)
      if(isChanged) {
        goRight(i)
      }

    }

    def goRight(i: Int):Unit = {
      var isChanged: Boolean = false
      (1 to m - 1) foreach (j => {
        isChanged = checkAndSetNewValue(i, j, j-1) || isChanged
      })
//      printArray(result(i), i)
      if(isChanged) {
        goLeft(i)
      }

    }

    (0 to n-1) foreach(i => {
      (0 to m - 1) foreach (j => {
        result(i)(j) = (i, j) match {
          case (0, 0) => Cell(input(i)(j), List(j))
          case (0, _) => newCell(i,j).addLeftOrRight(result(i)(j-1), input(i)(j), i, j)
          case (_, 0) => newCell(i,j).addTop(result(i-1)(j), j)
          case _ => {
            val maxTop: Int = result(i - 1)(j).result
            val maxLeft: Int = result(i)(j - 1).result
            if (maxTop > maxLeft)  newCell(i,j).addTop(result(i-1)(j), j)
            else newCell(i,j).addLeftOrRight(result(i)(j-1), input(i)(j), i, j)
          }
        }
      })
//      printArray(result(i), i)
      goLeft(i)
    })

//        printArray(result)

    var maxVal: Int = result(n-1).map(_.result).max

    println(maxVal)
  }


}
