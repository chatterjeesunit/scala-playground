package com.rockthejvm.scalabeginners.exercises

import scala.annotation.tailrec

object ListImplementation extends App {

  trait MyList {
    def isEmpty: Boolean
    def head: Int
    def tail: MyList
    def add(num: Int): MyList
  }

  object Empty extends MyList {
    def isEmpty: Boolean = true
    def head: Int = throw new NoSuchElementException
    def tail: MyList = throw new NoSuchElementException
    def add(num: Int): MyList = Cons(num, Empty)

    override def toString: String = "[]"
  }

  class Cons(val head: Int, val tail: MyList) extends MyList {
    def isEmpty: Boolean = false
    def add(num: Int): MyList = Cons(num, this)

    @tailrec
    private def print(tailList: MyList = this.tail, result: String = this.head.toString): String = {
      if(tailList.isEmpty) result
      else print(tailList.tail, s"${result} , ${tailList.head}")
    }

    override def toString: String = s"[ ${print()} ]"
  }


  val l1 = Empty
  val l2 = l1.add(5).add(6)
  val l3 = l1.add(9)
  val l4 = l2.add(10).add(33)
  val l5 = Cons(5, Cons(6, Cons(19, Empty)))


  println(l1)
  println(l2)
  println(l3)
  println(l4)
  println(l5)

}
