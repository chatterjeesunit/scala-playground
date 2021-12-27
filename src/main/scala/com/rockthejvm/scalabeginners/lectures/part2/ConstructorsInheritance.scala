package com.rockthejvm.scalabeginners.lectures.part2

import scala.util.Random

object ConstructorsInheritance extends App {

  abstract class Parent(val x: String, val y: Int) {

    def this(x: String) = this(x, Random.nextInt())

    def print = println(s"$x , $y")

  }

  class ChildOne(val a: String, val b: Int, val c: String) extends Parent(a, b)


  class ChildTwo(val a: String, val c: String) extends Parent(a)


  val c1: ChildOne = ChildOne("Test", -1, "Pass")

  val c2: ChildTwo = ChildTwo("Test", "Pass")


  c1.print
  c2.print

}
