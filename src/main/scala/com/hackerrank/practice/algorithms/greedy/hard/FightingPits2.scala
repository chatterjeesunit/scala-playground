package com.hackerrank.practice.algorithms.greedy.hard

import java.io.{BufferedReader, InputStreamReader}


/**
  * Created by sunitc on 10/31/17.
  */

/* Sample Input
7 2 6
1 1
2 1
1 1
1 2
1 2
1 2
2 2
2 1 2
2 2 1
1 2 1
1 2 1
2 1 2
2 2 1
 */
object FightingPits2 {

  case class FSC(strength: Int, count: Int) extends Ordered[FSC] {
    override def toString: String = s"[$strength, $count]"

    override def compare(that: FSC): Int = that.strength.compareTo(this.strength)
  }

  def main(args: Array[String]): Unit = {

    val bufferedReader: BufferedReader = new BufferedReader(new InputStreamReader(System.in))
    
    val n: Int = getInt(bufferedReader)
    val k: Int = getInt(bufferedReader)
    val q: Int = getInt(bufferedReader)


    var fighterTeams: Map[Int, List[Int]] = (1 to n).map(
      i => {
        val s: Int = getInt(bufferedReader)
        val t: Int = getInt(bufferedReader)
        (t, s)
      }).groupBy(ts => ts._1).map(kv => (kv._1, kv._2.map(_._2).toList.sorted))

    //    println(fighterTeams)

    def addFighterToTeam(team: Int, strength: Int) = {
      fighterTeams = fighterTeams.updated(team, strength :: fighterTeams.getOrElse(team, List[Int]()))
    }

    (1 to q) foreach (i => {
      //      println(fighterTeams)
      val mode: Int = getInt(bufferedReader)
      val x: Int = getInt(bufferedReader)
      val y: Int = getInt(bufferedReader)

      mode match {
        case 1 => addFighterToTeam(y, x)
        case _ => {
          var xTeam: List[Int] = fighterTeams.getOrElse(x, List[Int]())
          var yTeam: List[Int] = fighterTeams.getOrElse(y, List[Int]())
          var i: Int = xTeam.size - 1
          var j: Int = yTeam.size - 1
          while (i >= 0 && j >= 0) {
            j = j - xTeam(i)
            if (j >= 0) i = i - yTeam(j)
          }
          if (i >= 0) println(x) else println(y)
        }
      }
    })

    bufferedReader.close()

  }

  private def getInt(bufferedReader: BufferedReader): Int = {
    var char:Int = 0
    var isDigit : Boolean = false
    var returnValue: Int = 0
    var readNext: Boolean = true
    while(readNext) {
      char = bufferedReader.read()
      if(char == -1)
        readNext = false
      else if(char >= '0' && char <= '9'){
        isDigit = true
        returnValue = returnValue*10 + char - '0'
      } else if (isDigit) readNext = false
    }
    returnValue
  }
}
