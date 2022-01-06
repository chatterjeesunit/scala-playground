package com.rockthejvm.scalabeginners.lectures.part3

object FunctionCurryingOne extends App {

  //Add Method definition
  def add(x:Int, y:Int): Int = x + y

  //Add function
  val addFn: (Int, Int) => Int = _ + _

  //We can convert normal methods, functions to curried functions in this way below
  //It is converted to a curried function that takes an Int, and returns a Int=>Int function
  val addCurriedOne: Int => (Int => Int) = add.curried
  val addCurriedTwo: Int => (Int => Int) = addFn.curried

  //We can also create curried functions manually
  def sum(x:Int)(y:Int): Int = x + y


  //Now lets try to use all the normal add functions and curried functions
  //Results will be same for all of them
  //Result of all these function invocations will be 11
  add(5, 6)
  addFn(5, 6)
  addCurriedOne(5)(6)
  addCurriedTwo(5)(6)
  sum(5)(6)

  // Curried functions can also be used to create other functions
  // Creates a function that adds 10 to any number
  val addTen: Int => Int = sum(10)
  // Creates a function that adds 50 to any number
  val addFifty: Int => Int = sum(50)

  //We can then use these new functions as below
  addTen(25) //Result will be 35
  addFifty(25) //Result will be 75

}
