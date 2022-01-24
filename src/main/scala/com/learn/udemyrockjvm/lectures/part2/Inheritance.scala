package com.learn.udemyrockjvm.lectures.part2

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

  class Rabbit(override val animalType: String) extends Animal {
    def sound(): String = "Squeals"
  }
  
  val dog: Animal = new Dog
  val rabbit: Animal = Rabbit("Rabbit")

  dog.makeSound()
  rabbit.makeSound()
}
