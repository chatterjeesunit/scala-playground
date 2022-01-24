package com.learn.udemyrockjvm.lectures.part2

import org.joda.time.{Days, LocalDate}

import java.util.Date

object ClassBasics extends App{

  class Person( val firstName: String, val lastName: String, age: Int, val middleName: String = "") {

    val fullName: String = s"$firstName $lastName"

    def this(firstName: String, lastName: String, dateOfBirth:LocalDate) =
      this(firstName, lastName,  Days.daysBetween(LocalDate.now(), dateOfBirth ).getDays )

    def hello() = println(s"Hello $fullName")
  }

  val johndoe:Person = Person( "John", "Doe", 35)

  val janedoe: Person = Person("Jane", "Doe", LocalDate(1990, 1, 1))

  println(s"${johndoe.fullName} : FirstName = ${johndoe.firstName} , LastName: ${johndoe.lastName}")

  johndoe.hello()

  janedoe.hello()

}





