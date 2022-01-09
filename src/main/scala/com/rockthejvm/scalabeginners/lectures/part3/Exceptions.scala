package com.rockthejvm.scalabeginners.lectures.part3

import scala.util.{Failure, Random, Success, Try}

object Exceptions extends App {

  val list = List("Hulk", "Iron Man", "Black Widow", "Captain America", "Doctor Strange")

  def getSuperHero(index: Int): Try[Option[String]] = Try {
    println(s"fetching random superhero at index = $index")
    if (index == 10 || index == 0) throw new RuntimeException("Just simulating a random exception.")

    val superhero: String = list(index)
    Some(superhero)
  }


  val superHero: Option[String] =
    getSuperHero(Random().nextInt(10)) match {
      case Success(hero) => hero
      case Failure(exception) => exception match {
        case e: ArrayIndexOutOfBoundsException =>
          println("Got an ArrayIndexOutOfBoundException")
          None
        case e: Throwable =>
          println(s"Got a random Runtime Exception: ${e}")
          None
      }
    }

  superHero.foreach(hero => println(s"Superhero = $hero"))

}

