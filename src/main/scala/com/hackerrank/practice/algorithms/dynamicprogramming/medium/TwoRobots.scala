package com.hackerrank.practice.algorithms.dynamicprogramming.medium

import java.util.Scanner

/*
https://www.hackerrank.com/challenges/two-robots/problem

Sample Test Case
4
5 4
1 5
3 2
4 1
2 4
4 2
1 2
4 3
10 3
2 4
5 4
9 8
10 8
5 10
10 2
9 10
3 6
6 9
2 9
2 8
6 5

Sample Output
11
2
5
50

  */
object TwoRobots {

  case class RobotMove(xPos:Int = 0, xDistance:Int = 0, yPos:Int = 0, yDistance:Int= 0) {
    def totalDistance = xDistance + yDistance

    def moveX(start:Int, end:Int): RobotMove = {
      val pos = end
      val distance =
        xDistance + Math.abs(start - end) + (xPos match {
          case 0 => 0
          case _ => Math.abs(xPos - start)
        })

      this.copy(xPos = pos, xDistance = distance)
    }


    def moveY(start:Int, end:Int): RobotMove = {
      val pos = end
      val distance =
        yDistance + Math.abs(start - end) + (yPos match {
          case 0 => 0
          case _ => Math.abs(yPos - start)
        })

      this.copy(yPos = pos, yDistance = distance)
    }

    def bothRobotInSamePos = xPos == yPos
  }

  def main(arr: Array[String]):Unit = {
    val sc: Scanner = new Scanner(System.in);
    val numTestCases: Int = sc.nextInt();
    (1 to numTestCases) foreach( t => {
      val (m:Int, n:Int) = readPair(sc);
      var robotMoves: List[RobotMove] = List(new RobotMove())

      (1 to n) foreach(moves => {

        val (start:Int, end:Int) = readPair(sc)
        var newRobotMoves: List[RobotMove] = List()
        robotMoves.foreach(currentRobotMove => {
          if(moves == 1) {
            //move any ... so lets move x only
            newRobotMoves = currentRobotMove.moveX(start, end)::newRobotMoves
          }else {
            newRobotMoves = currentRobotMove.moveX(start, end)::newRobotMoves
            newRobotMoves = currentRobotMove.moveY(start, end)::newRobotMoves
          }
        })
        robotMoves = newRobotMoves

      })
      println(getBestRobotMove(robotMoves).totalDistance)
    })
  }

  def getBestRobotMove(robotMoves: List[RobotMove]): RobotMove = {
    robotMoves.sortBy(r => r.totalDistance).toList(0)
  }


  def readPair(sc:Scanner): (Int,Int) = {
    //val numbers:List[Int] = sc.nextLine().trim.split(" ").toList.map(_.toInt).toList

    //  (numbers(0), numbers(1))
    (sc.nextInt(), sc.nextInt())
  }
}
