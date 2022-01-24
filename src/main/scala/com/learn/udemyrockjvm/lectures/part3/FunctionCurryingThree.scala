package com.learn.udemyrockjvm.lectures.part3

object FunctionCurryingThree extends App {

  def modN(divisibleBy:Int)(number:Int): Boolean = number % divisibleBy == 0


  val divisibleBy2 = modN(2)
  val divisibleBy3 = modN(3)
  val divisibleBy5 = modN(5)

  //Lets see how we can now use these curried functions
  val list = List( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

  val l1 = list.filter(divisibleBy2)  // Returns a list [2, 4, 6, 8, 10, 12, 14]
  val l2 = list.filter(divisibleBy3)  // Returns a list [3, 6, 9, 12, 15]
  val l3 = list.filter(divisibleBy5)  // Returns a list [5, 10, 15]

  println(list)
  println(l1)
  println(l2)
  println(l3)


}
