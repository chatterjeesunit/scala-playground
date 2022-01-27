package com.learn.udemyrockjvm.exercises

object ComponseAndThen extends App {

  def compose(f: Int => Int, g:Int => Int): Int => Int = {
    def newFn(f: Int => Int)(g:Int => Int)(x: Int): Int = {
      val result:Int = g(x)
      val finalResult: Int = f(result)
      finalResult
    }
    newFn(f)(g)
  }


  def andThen(f: Int => Int, g:Int => Int): Int => Int = {
    def newFn(f: Int => Int)(g:Int => Int)(x: Int): Int = {
      val result:Int = f(x)
      val finalResult: Int = g(result)
      finalResult
    }
    newFn(f)(g)
  }

  def composeNew(f: Int => Int, g:Int => Int): Int => Int = x => f(g(x))
  def andThenNew(f: Int => Int, g:Int => Int): Int => Int = x => g(f(x))




  val result1 = compose(x => x * x, x => x * 10)(4)
  val result2 = composeNew(x => x * x, x => x * 10)(4)

  val result3 = andThen(x => x * x, x => x * 10)(4)
  val result4 = andThenNew(x => x * x, x => x * 10)(4)

  println(result1)
  println(result2)
  println(result3)
  println(result4)

}
