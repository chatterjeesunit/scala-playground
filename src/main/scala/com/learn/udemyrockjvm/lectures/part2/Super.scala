package com.learn.udemyrockjvm.lectures.part2

object Super extends App {

  abstract class Parent(val x: Int, val y: Int) {

    override def toString: String = s"${super.toString} :: ($x, $y)"

    def sum: Int = x + y

  }


  class Child(val a: Int, b:Int, c:Int) extends Parent(a, b) {

    override def toString: String = s"${super.toString} :: ($c)"

    override def sum: Int = super.sum + c
  }


  val c: Child = Child(10, 30, 50)

  println(c)
  println(c.sum)
}
