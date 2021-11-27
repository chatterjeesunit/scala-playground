package com.rockthejvm.scalabeginners.lectures.part1

object Functions extends App {
  //A Simplest function
  def add(a: Int, b: Int): Int = {
    a + b
  }

  //Return type is not mandatory and function body can be an expression too
  def product(a: Int, b: Int) = a * b

  //Calling functions
  val sum = add(45, 33)
  println(s"45 + 33 = ${sum}")


  def sumOfSquare(a: Int, b: Int): Int = {
    def add(x: Int, y: Int) = x + y

    def square(x: Int) = x * x

    add(square(a), square(b))
  }

  println(s"Sum of Squares of 5 and 7 is ${sumOfSquare(5, 7)}")
}
