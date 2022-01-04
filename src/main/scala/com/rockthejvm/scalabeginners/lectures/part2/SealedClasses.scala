package com.rockthejvm.scalabeginners.lectures.part2

object SealedClasses extends App {

  val animalOne: MyAnimal = MyDog()
  val animalTwo: MyAnimal = MyCat()

  animalOne.makeSound()
  animalTwo.makeSound()

}



sealed abstract class MyAnimal(val name: String) {
  def makeSound(): Unit
}


class MyDog extends MyAnimal("Dog") {
  def makeSound(): Unit = println(s"${name} barks.")
}

class MyCat extends MyAnimal("Cat") {
  def makeSound(): Unit = println(s"${name} meows.")
}
