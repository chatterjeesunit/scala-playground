package com.rockthejvm.scalabeginners.exercises

object CurryUncurry extends App {


  def toCurry(fn: (Int, Int)=>Int): Int => Int => Int = x => y => fn(x, y)

  def fromCurry(fn: Int => Int => Int): (Int, Int) => Int = (x,y) => fn(x)(y)



  def add(x:Int, y:Int): Int = x + y

  def sum(x:Int)(y:Int): Int = x + y


  val addCurried: Int => Int => Int = toCurry(add)
  println(addCurried(5)(6))


  val sumUncurried: (Int, Int) => Int = fromCurry(sum)
  println(sumUncurried(5, 6))


}
