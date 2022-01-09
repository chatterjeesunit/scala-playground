package com.rockthejvm.scalabeginners.lectures.part3

object CollectionsTuples extends App {

  //Tuples

  // can create from Tuple1 onwards to Tuple22
  val t1: Tuple1[Int] = Tuple1(15)    //Single element tuple
  val t2: Tuple2[String, Int] = Tuple2("Sam", 2001) // Tuple with 2 elements
  val t3: Tuple3[String, Int, Boolean] = Tuple3("Neo", 2004, false) // Tuple with 3 elements

  //can also create tuples likes this
  val tupleNew: Tuple2[String, Int] = ("Sam", 2001) // Tuple with 2 elements
  val anotherTuple: Tuple3[String, Int, Boolean] = ("Neo", 2004, false) // Tuple with 3 elements


  //Elements of tuple are access by ._1, ._2, ._3, and so on
  val name: String = anotherTuple._1   // Will return value "Sam"
  val year: Int = anotherTuple._2      // Will return the value - 2004
  val isActive: Boolean = anotherTuple._3 //Will return the value as false

  //Or we can do it in one single step (via extraction)
  val (anotherName: String, anotherYear:Int, isActiveFlag:Boolean) = anotherTuple

  println(anotherTuple) // (Neo,2004,false)
  println(anotherName)  // Neo
  println(anotherYear)  // 2004
  println(isActiveFlag) //  false

  //creating new tuples
  val newTuple = anotherTuple.copy(_3 = true) // (Neo,2004,true)

}
