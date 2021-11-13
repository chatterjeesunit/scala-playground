package com.rockthejvm.scalabeginners.lectures

import scala.util.Random

object Expressions extends App {

  //Simple Expressions
  val a = 1 + 2
  val b = "Hello"
  val c = "Sunit"
  val d = b + c
  val e = (a < 5)

  println(s"a=$a, b=$b, c=$c, d=$d, e=$e")

  //If Expressions
  //If is not an statment in scala but an expression.
  //If is an expression that is evaluated and returns a value
  val num = if (c.length > 10) 100 else 50
  println(s"num=${num}")

  //Another example of IF Expression
  //Note that the if block can have multiple lines of code.
  //The value of last line of the block is returned by the if block automatically
  val n1 = Random.nextInt(100)
  val n2 = Random.nextInt(100)

  val result = if (n1 > n2) {
    println("N1 is bigger")
    n1 - n2
  } else if (n1 == n2) {
    println("N1 and N2 are equal")
    0
  } else {
    println("N2 is bigger")
    n2 - n1
  }
  println(s"N1 = ${n1}, N2 = ${n2}, Difference = ${result}")


  //While is also an expression that returns an Unit
  //We should never use while like this in scala to loop and do something
  var counter = 0
  val test: Unit = while (counter < 10) {
    println(counter)
    counter += 1
  }

  println(s"test=${test}, type of test = ${test.getClass}")


  //Code blocks are also Expressions
  //Value returned by code block are the value of the last expression of code block
  //Val,vars defined within code blocks are only visible within code block
  val anotherResult = {
    val n1 = Random.nextInt(100)
    val n2 = Random.nextInt(100)

    val n3 = if (n1 < n2) n1 else n2

    (n1 + n2) / n3
    println(s"$n1, $n2, $n3")
  }
  println(s"anotherResult = ${anotherResult}")

}
