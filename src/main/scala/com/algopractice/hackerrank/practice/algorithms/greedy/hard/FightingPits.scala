package com.hackerrank.practice.algorithms.greedy.hard

import java.io.{BufferedReader, InputStreamReader}
import scala.collection.mutable.ListBuffer


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
object FightingPits {

  case class FSC(strength: Int, count: Int) extends Ordered[FSC] {
    override def toString: String = s"[$strength, $count]"

    override def compare(that: FSC): Int = that.strength.compareTo(this.strength)
  }

  case class Query(mode: Int, x: Int, y:Int)

  def main(args: Array[String]): Unit = {

    val bufferedReader: BufferedReader = new BufferedReader(new InputStreamReader(System.in))

    val n: Int = getInt(bufferedReader)
    val k: Int = getInt(bufferedReader)
    val q: Int = getInt(bufferedReader)

    var fighterTeams: Map[Int, List[FSC]] = (1 to n).map(
      i => {
        val s: Int = getInt(bufferedReader)
        val t: Int = getInt(bufferedReader)
        (t, s)
      }).groupBy(ts => ts._1)
      .map { case (t, f) => (t,
        f.groupBy(_._2).mapValues(_.size).map(kv => FSC(kv._1, kv._2)).toList.sorted)
      }
//    println(fighterTeams)
    var queries: ListBuffer[Query] = new ListBuffer[Query]()
    (1 to q).foreach ( i => {
        val m: Int = getInt(bufferedReader)
        val x: Int = getInt(bufferedReader)
        val y: Int = getInt(bufferedReader)
        queries+=Query(m, x, y)
      }
    )
//    println(s"Total Queries = ${queries.size}")
//    println(queries)

//    bufferedReader.close()
    def addFighterToTeam(team: Int, strength: Int) = {
      val fighterTeamOption: Option[List[FSC]] = fighterTeams.get(team)
      fighterTeamOption match {
        case Some(ftList) => { ftList.head.strength match {
          case `strength` => {
            fighterTeams = fighterTeams.updated(team, FSC(strength, ftList.head.count + 1) :: ftList.tail)
          }
          case _ => {
            fighterTeams = fighterTeams.updated(team, FSC(strength, 1) :: ftList)
          }
        }}
        case _ => List(FSC(strength, 1))
      }
    }

    def removeFighter(n: Int, list: List[FSC]): List[FSC] = {
      var rem: Int = n
      var updatedList: List[FSC] = list
      rem -= updatedList.head.count
      while (rem > 0 && !updatedList.isEmpty) {
        updatedList = updatedList.tail
      }
      if (!updatedList.isEmpty) {
        val oldFSC: FSC = updatedList.head
        updatedList = FSC(oldFSC.strength, Math.abs(rem)) :: updatedList.tail
      }
      updatedList
    }

    queries foreach (q => {
      //      println(fighterTeams)
      val mode: Int = q.mode
      val x: Int = q.x
      val y: Int = q.y

      mode match {
        case 1 => {
          addFighterToTeam(y, x)
//          println(fighterTeams)
        }
        case _ => {
          if (x == y) println(x)
          else {

            var xTeam: List[FSC] = fighterTeams.getOrElse(x, List[FSC]())
            var yTeam: List[FSC] = fighterTeams.getOrElse(y, List[FSC]())
            val xn = xTeam.size
            val yn = yTeam.size

//            println(s"q = $q, x = $x, y= $y, xn = $xn, yn = $yn")
            var xi: Int = 0 // xcounter
            var xs: Int = xTeam(xi).strength //current x strength
            var xc: Int = xTeam(xi).count //fighters left with current strength

            var yi: Int = 0 // ycounter
            var ys: Int = yTeam(yi).strength //current y strength
            var yc: Int = yTeam(yi).count //fighters left with current strength

            var xChance: Boolean = true

            while (xi < xn && yi < yn) {
              val rdx: Int = xc / ys
              val rdy: Int = yc / xs
              var rrx: Int = xc % ys
              var rry: Int = yc % xs

              val reduce: Int = Math.min(rdx, rdy)

              if (xChance && reduce > 0) { // Reduce in multiples
                xc -= ys * reduce
                yc -= xs * reduce
              } else {
                if(xChance){
                  if(rdy > 0 && xc > 0) {
                    yc -= xs
                  }
                  else {
                    var remaining: Int = xs
                    while (remaining > 0 && yi < n) {
                      val remOrig:Int = remaining
                      remaining -= yc
                      yc -= remOrig

                      if(yc <= 0) {
                        yi += 1
                        if(yi < yn){
                          ys = yTeam(yi).strength
                          yc = yTeam(yi).count
                        }
                      }
                    }
                  }
                } else {
                  if(rdx > 0 && yc > 0) {
                    xc -= ys
                  }else {
                    var remaining: Int = ys
                    while (remaining > 0 && xi < n) {
                      val remOrig:Int = remaining
                      remaining -= xc
                      xc -= remOrig

                      if(xc <= 0) {
                        xi += 1
                        if(xi < xn) {
                          xs = xTeam(xi).strength
                          xc = xTeam(xi).count
                        }
                      }
                    }
                  }
                }
                xChance = !xChance
              }
            }
            if(xi >= xn) println(y) else println(x)
          }
        }
      }
    })
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
