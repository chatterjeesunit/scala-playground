package com.rockthejvm.scalabeginners.lectures.part2

object Traits extends App{


  trait MoveBehavior {
    def move(): Unit
  }

  class Walk extends MoveBehavior {
    def move() = println("I am walking")
  }
  class Fly extends MoveBehavior {
    def move() = println("I am flying")
  }
  class Swim extends MoveBehavior {
    def move() = println("I am swimming in water.")
  }


  abstract class Animal(val name: String, val moveBehavior: MoveBehavior) {
    def describeMe(): Unit = {
      println(s"I am a ${name}")
      moveBehavior.move()
    }
  }

  class Cat extends Animal("Cat", new Walk())
  class Fish extends Animal("Fish", new Swim())
  class Bird extends Animal("Bird", new Fly())

  val cat: Animal = new Cat()
  val bird: Animal = new Bird()
  val fish: Animal = new Fish()


  cat.describeMe()
  bird.describeMe()
  fish.describeMe()

}




