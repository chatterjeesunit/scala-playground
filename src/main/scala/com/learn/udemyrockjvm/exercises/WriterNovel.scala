package com.rockthejvm.scalabeginners.exercises

import org.joda.time.LocalDate
import scala.language.postfixOps

object WriterNovel extends App {

  val writer: Writer = Writer("John", "Doe", 1990)

  val novel: Novel = Novel("Horror Stories", 2000, writer)

  val novelReleasedAgain = novel.copy(2002)

  println(s"${novel.name} is first written by ${novel.author.fullName()} in year ${novel.yearOfRelease}")

  println(s"${novelReleasedAgain.name} is released again byy ${novelReleasedAgain.author.fullName()} in year ${novelReleasedAgain.yearOfRelease}")

  val counter: Counter = Counter(10)

  //For using postfix operators, you need to add this import `import scala.language.postfixOps`
  val counter1 = counter.decrement()
  val counter2 = counter1.increment().increment().increment()
  println(counter1.counterVal)
  println(counter2.counterVal)


}

class Writer(val firstName: String, val surname: String, val yearOfBirth: Int) {
  def fullName():String = s"$firstName $surname"
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {

  def authorAge:Int = yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = author.fullName().equals(this.author.fullName())

  def copy(newYearOfRelease: Int): Novel = Novel(this.name, yearOfRelease, this.author)

}

class Counter(val counterVal: Int) {

  def getCurrentCount():Int = this.counterVal

  //For using postfix operators, you need to add this import `import scala.language.postfixOps`
  def decrement(): Counter = this - 1
  def increment(): Counter = this + 1

  def +(increment:Int) = Counter(counterVal + increment)

  def -(decrement:Int) = Counter(counterVal - decrement)

}