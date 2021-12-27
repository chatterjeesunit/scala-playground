package com.rockthejvm.scalabeginners.lectures.part2

object SealedClasses extends App {

  val animalOne: Animal = MyDog()
  val animalTwo: Animal = MyCat()

  animalOne.makeSound()
  animalTwo.makeSound()

}



sealed abstract class Animal(val name: String) {
  def makeSound(): Unit
}


class MyDog extends Animal("Dog") {
  def makeSound(): Unit = println(s"${name} barks.")
}

class MyCat extends Animal("Cat") {
  def makeSound(): Unit = println(s"${name} meows.")
}
