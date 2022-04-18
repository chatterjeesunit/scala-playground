package com.learn.udemyrockjvm.lectures.part4afp

import scala.util.Try

object PartialFunctions extends App {


  // A function that only calculates square of even numbers
  val sqFn : Int => Int = num => num match {
    case n if n % 2 == 0 => n * n
    case default => throw new UnsupportedOperationException
  }


  // A similar function can be written as following partial function
  // it will throw MatchError, if it receives any non even number as input
  // returns square of even numbers
  val sqEvenFn: PartialFunction[Int, Int] = {
    case n if n % 2 == 0 => n * n
  }

  // returns cube of odd numbers
  val cubeOddFn: PartialFunction[Int, Int] = {
    case n if n % 2 != 0 => n * n * n
  }

  // this will throw match error
//  List(2, 4, 6, 9) foreach (x =>
//    println(s"Square of $x = ${sqEvenFn(x)}")
//    )

  println(sqEvenFn isDefinedAt 10) // true

  println(sqEvenFn isDefinedAt 9) // false


  println(List(2, 4, 6, 9) map sqEvenFn.lift)


  // OrElse
  println(List(2, 4, 6, 9) map (sqEvenFn orElse cubeOddFn))


  // andThen

  val squareOfInts: PartialFunction[Any, Int] = {
    case x: Int => x * x
  }

  val divideIntsBy10: PartialFunction[Any, Int] = {
    case x: Int => x/10
  }

  val incrementInts: PartialFunction[Any, Int] = {
    case x: Int => x + 1
  }

  // we can chain all partial functions one after other

  // Output will be List(4, 16, 36, 81)
  println(List(2, 4, 6, 9) map squareOfInts)

  // Output will be List(0, 1, 3, 8)
  println(List(2, 4, 6, 9) map (squareOfInts andThen divideIntsBy10))

  // Output will be List(1, 2, 4, 9)
  println(List(2, 4, 6, 9) map
    (squareOfInts andThen divideIntsBy10 andThen incrementInts))


  // Will throw scala.MatchError: abc (of class java.lang.String)
//  List("abc", 2, "c" , 9) map squareOfInts

  // Will return List(4, 81)
  println( List("abc", 2, "c" , 9) collect squareOfInts)



  println(
    Try(
    List("abc", 2, "c" , 9) map squareOfInts
  ) recover {
    case e: MatchError => List("abc", 2, "c" , 9) collect squareOfInts
  })



}
