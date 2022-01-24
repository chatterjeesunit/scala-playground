package com.learn.udemyrockjvm.lectures.part3

object FunctionsAsObjects extends App {

  //A simple function example in Scala that takes an Int arg and returns an Int
  def square(num: Int): Int = num * num

  //We call this function like this
  val result = square(5)
  println(result)

  // This is the actual function class - implementation of the `Function1` trait
  val squareFn: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * v1
  }

  // To use this we can call
  squareFn.apply(50)

  // An add function to add two numbers
  def add(x: Int, y: Int): Int = x + y

  //Similarly an add function that takes two arguments,
  //is equivalent to an implementation of `Function2` trait
  val addFn: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  //We can call apply method again to use this function
  val addResult = addFn.apply(5, 10)
  println(addResult)

  // But Scala provides some syntactical sugar to avoid writing so much boiler plate code
  // - Type of `Function1[Int, Int]` can be written as Int => Int
  // - instead of creating new class and overrriding the `apply` method, we can write it like this
  val squareFnNew: Int => Int = value => value * value
  val addFnNew: (Int, Int) => Int = (a, b) => a + b

  println(s"Square of 15 = ${squareFnNew(15)}")
  println(s"35 + 42 = ${addFnNew(35, 42)}")
}
