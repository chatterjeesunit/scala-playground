package com.rockthejvm.scalabeginners.lectures.part3

object Collections extends App {

  // SEQUENCES
  val seqofInts: Seq[Int] = Seq( 4, 5, 7, 8)  //Creating Sequences

  seqofInts(2)                          // Access element at index 2 in the seq

  seqofInts.updated(2, 0)               //Return a new updated sequence

  Seq.fill(3)(false)                    //Creates a sequence of size 3 prefilled with false value

  Seq.fill(2, 3)(false)                 //Creates a sequence of sequence, prefilled with false

  seqofInts.foreach( x => println(x))   //use foreach to interate and perform an action

  seqofInts.mkString(" -> ")            //can convert it to a string. e.g. "4 -> 5 -> 7 -> 8"

  seqofInts.reverse                     //can reverse a sequence

  seqofInts.sorted                      //can sort a sequence

  seqofInts ++ Seq(6, 3, 1, 5)          //can append two sequences using ++ operator

  9 +: seqofInts :+ 10                  //to prepend and append elements to a seq

  Seq(1, 5, 2, 5, 7, 3, 2).distinct     //can get distinct elements from the sequence

  seqofInts.filter(_ % 2 == 0)          //can filter a seq

  seqofInts.map(x => x * x)             //can map a seq




  // RANGES

  val rangeInclusive = 1 to 10
  rangeInclusive.foreach(x => println(x))

  val rangeExclusive = 1 until 10
  rangeInclusive.foreach(x => println(x))

  val rangeWithStep = 1 to (10,2)  // 1 3 5 7 9
  rangeWithStep.foreach(x => println(x))

  //Range can be converted to seq
  val seq: Seq[Int] = 1 until 10





  // LISTS

  val listOfInts: List[Int] = List( 4, 5, 7, 8)  //Creating Lists

  listOfInts(2)                          // Access element at index 2 in the list

  listOfInts.updated(2, 0)               //Return a new updated list

  List.fill(3)(false)                    //Creates a list of size 3 prefilled with false value

  List.fill(2, 3)(false)                 //Creates a list of list, prefilled with false

  listOfInts.foreach( x => println(x))   //use foreach to interate and perform an action

  listOfInts.mkString(" -> ")            //can convert it to a string. e.g. "4 -> 5 -> 7 -> 8"

  listOfInts.reverse                     //can reverse a list

  listOfInts.sorted                      //can sort a list

  listOfInts ++ List(6, 3, 1, 5)          //can append two lists using ++ operator

  9 +: listOfInts :+ 10                  //to prepend and append elements to a list

  List(1, 5, 2, 5, 7, 3, 2).distinct     //can get distinct elements from the list

  listOfInts.filter(_ % 2 == 0)          //can filter a list

  listOfInts.map(x => x * x)             //can map a list





  //Arrays

  val arr: Array[Int] = Array( 4, 9, 11, 7)   // create the array

  val arr1 = Array.ofDim[Int](5)        // 5 element array filled with 0
  val arr2 = Array.ofDim[String](5)     // 5 element array filled with null
  val arr3 = Array.ofDim[Boolean](5)    // 5 element array filled with false

  val element = arr(3)      // fetch array element at index 3 (starting with 0 index)

  arr.updated(3, 15)        // creates a new array with element at 3 index updated to 15
  arr.update(3, 13)         // In place mutation/updated of array element
  arr(3) = 10               // Syntactic sugar for inplace mutation of array

  val seqInts: Seq[Int] = arr //Converts the array to Seq



  //Vector
  val vector = Vector(1 ,2, 3)
  // almost same operations as lists

  //Maps

  //Tuples
}
