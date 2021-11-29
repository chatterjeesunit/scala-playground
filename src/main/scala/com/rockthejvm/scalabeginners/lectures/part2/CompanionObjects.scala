package com.rockthejvm.scalabeginners.lectures.part2

import com.rockthejvm.scalabeginners.lectures.part2.Person.{AGE_LESS_THAN_ZERO_ERROR, MIN_AGE}


object CompanionObjects extends App {

  val p1 = new Person("John Doe", 0)

  val p2 = Person.apply("John", "Doe", 35)

  val p3 = Person("John", "Doe", 35)

  println(s"Min age for persons is ${Person.MIN_AGE + 1}")

}

object Person {
  val MIN_AGE: Int = 0
  val AGE_LESS_THAN_ZERO_ERROR = s"Age cannot be less than $MIN_AGE"

  def apply(firstName: String, lastName: String, age: Int) =
    new Person(s"$firstName $lastName", age)
}

class Person(val name: String, val age: Int) {
  require(age > MIN_AGE, AGE_LESS_THAN_ZERO_ERROR)
}