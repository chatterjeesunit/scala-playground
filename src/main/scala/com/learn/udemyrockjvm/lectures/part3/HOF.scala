package com.learn.udemyrockjvm.lectures.part3

object HOF extends App {

  //Higher order functions that take other functions as parameter

  // Example one - Calculator function
  // Takes an function as argument - argument can be an add function or multiply function, etc
  // performs the calculation based on whatever the function is passed to it
  def calculate(x:Int, y:Int, fn: (Int, Int) => Int): Int = fn(x, y)

  // calculates the sum
  val sum = calculate(5, 6, _ + _ )  //Result = 15

  // calculates multiplication
  val product = calculate(5, 6, _ * _ ) // Result = 30

  // calculates sum of squares
  val sumOfSquares = calculate(5, 6, (x, y) => x*x + y*y )  // Result = 61





  val listOfIntegers = List(12, 45, 32, 3, 67, 23, 23, 7, 2, 6, 22)

  //Filter even numbers
  listOfIntegers.filter( _ % 2 == 0) //Result =  List(12, 32, 2, 6, 22)

  //Filter odd numbers
  listOfIntegers.filterNot( _ % 2 != 0) //Result = List(45, 3, 67, 23, 23, 7)



  // Function returning other functions
  // Example we want to create a greet function that greets the user in different ways
  // E.g Hello John Doe, or Good Morning John Doe
  // We want to create a generic function to do that
  // Below is a `greet` function that returns a function String => String

  def greet(greeting: String): String => String =
    name => s"$greeting $name."

  // New function `sayHello` created, using the return value from `greet` function
  val sayHello: String => String = greet("Hello")

  // New function `sayGoodMorning` created, using the return value from `greet` function
  val sayGoodMorning: String => String = greet("Good Morning")

  // Another function created to say hello in Spanish
  val sayHelloInSpanish: String => String = greet("Hola")


  //Now lets use the new functions creatd from return value of `greet`

  println( sayHello("John Doe") )           // prints "Hello John Doe."
  println( sayGoodMorning("John Doe") )     // prints "Good Morning John Doe."
  println( sayHelloInSpanish("John Doe") )  // prints "Hola John Doe."

}
