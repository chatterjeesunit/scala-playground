package com.rockthejvm.scalabeginners.exercises

import scala.language.postfixOps

object PersonMethodNotations extends App {

  class Person(name: String, favoriteMovie: String, val age: Int) {

    def + (title: String): Person = Person(s"$name ($title)", favoriteMovie, age)

    def unary_+ : Person = Person(name, favoriteMovie, age+1)

    def learns(course: String): String = s"$name learns $course"

    def learnsScala: String = learns("Scala")

    def apply(n: Int) = s"$name has watched movie $favoriteMovie $n times"
    
  }


  val mary = Person("Mary", "Inception", 35)

  println(s"Age of mary = ${mary.age}")

  val olderMary = +mary
  println(s"Age of Older mary = ${olderMary.age}")

  val maryWithTitle = mary + "Lead Consultant"

  println(maryWithTitle learns "Scala")

  println(maryWithTitle learnsScala)

  println(maryWithTitle(5))
}
