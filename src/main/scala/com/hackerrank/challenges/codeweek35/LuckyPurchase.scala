package com.hackerrank.challenges.codeweek35

import java.util.Scanner

import scala.collection.mutable
import scala.util.matching.Regex

/**
  * https://www.hackerrank.com/contests/w35/challenges/lucky-purchase
  */

/*
Sample
8
HackerBook 777444
RankBook 3
TheBook 777
BestBook 4747
HackerBook 47
JustBook 44
ABCBooks 77
Dummy 4744

Output -> HackerBook
 */
object LuckyPurchase {


  case class Laptop(name: String, price: BigInt) extends Ordered[Laptop] {
    private val validNumRegex: Regex = """(([4]+[7]+)|([7]+[4]+))+""".r

    def isValid(): Boolean = {
      val priceStr = price.toString
//      println(priceStr)
//      println(priceStr.length)
      if((priceStr.length % 2) != 0) false
      else {
        priceStr match {
          case validNumRegex(_,_,_) => true
          case _ => false
        }
      }
    }

    override def compare(that: Laptop): Int = that.price compare this.price

    override def toString: String = s"[$name, $price]"
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt
    var minPQ: mutable.PriorityQueue[Laptop] = new mutable.PriorityQueue[Laptop]()
    (1 to n) foreach(i => {
      val s = sc.next
      val n = sc.nextInt
      val laptop: Laptop = Laptop(s, n)
      if(laptop.isValid()) {
        minPQ += laptop
      }
    })

    println(minPQ)
    if(minPQ.isEmpty) println(-1)
    else println(minPQ.head.name)
  }
}
