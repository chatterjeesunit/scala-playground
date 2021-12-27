package com.rockthejvm.scalabeginners.lectures.part2

object Inheritance extends App {

  abstract class Animal() {
    val animalType: String
    def sound(): String
    def makeSound(): Unit = println(s"$animalType : ${sound()}")
  }

  class Dog extends Animal {
    val animalType: String = "Dog"
    def sound(): String = "Barks"
  }

  class Cat(override val animalType: String) extends Animal {
    def sound(): String = "Meows"
  }
  
  val dog: Animal = new Dog
  val cat: Animal = Cat("Cat")
  
  dog.makeSound()
  cat.makeSound()
}
