package com.learn.udemyrockjvm.lectures.part3

object FunctionCurryingTwo extends App {


  def operate(fn: Int => Int)(combine:(Int, Int) => Int)(x: Int)(y: Int): Int = combine(fn(x), fn(y))

  val identifyFn = (x:Int) => x
  val squareFn = (x: Int) => x * x
  val cubeFn = (x:Int) => x * x * x
  val addTwoNumbers = (x:Int, y:Int) => x + y
  val multiplyTwoNumbers = (x:Int, y:Int) => x * y

  val sum = operate(identifyFn)(addTwoNumbers)
  val product = operate(identifyFn)(multiplyTwoNumbers)
  val sumOfSquares = operate(squareFn)(addTwoNumbers)
  val sumOfCubes = operate(cubeFn)(addTwoNumbers)
  val productOfCubes = operate(cubeFn)(multiplyTwoNumbers)


  sum(5)(6)             //Result = 11
  product(5)(6)         //Result = 30
  sumOfSquares(5)(6)    //Result = 61
  sumOfCubes(5)(6)      //Result = 341
  productOfCubes(5)(6)  //Result = 27000

}
