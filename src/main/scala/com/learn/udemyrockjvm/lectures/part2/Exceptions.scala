package com.learn.udemyrockjvm.lectures.part2

import scala.util.Random

object Exceptions extends App {

  val list = List("Hulk", "Iron Man", "Black Widow", "Captain America", "Doctor Strange")

  def getSuperHero(index: Int): Option[String] = {
    try {
      println(s"fetching random superhero at index = $index")
      if(index == 10 || index == 0) throw new RuntimeException("Just simulating a random exception.")

      val superhero:String = list(index)
      Some(superhero)

    }catch {
      case e1: ArrayIndexOutOfBoundsException => {
        println("Got an ArrayIndexOutOfBoundException")
        None
      }
      case e2: Throwable => {
        println(s"Got a random Runtime Exception: ${e2}")
        None
      }
    }finally {
      println("Inside finally block, which is always executed")
    }
  }

  val superhero: Option[String] = getSuperHero(new Random().nextInt(10));
  println(s"Superhero = $superhero")
}

