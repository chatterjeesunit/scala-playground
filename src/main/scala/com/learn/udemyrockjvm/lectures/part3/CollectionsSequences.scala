package com.learn.udemyrockjvm.lectures.part3

object CollectionsSequences extends App {

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

}
