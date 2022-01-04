package com.rockthejvm.scalabeginners.lectures.part2

import com.rockthejvm.scalabeginners.exercises.{MyEmpty, MyLinkedList}


object CoVariance extends App {

  /********************* COVARIANCE ***************************/
  def printAnimals(animals: List[Animal]) = {
    animals.foreach(println(_))
  }

  val dogs: List[Dog] = List(Dog("Tommy"), Dog("Storm"))
  val cats: List[Cat] = List(Cat("Simba"), Cat("Poo"))

  //We can pass both list of dogs and list of cats to method printAnimals
  //since list is co-variant in Scala
  printAnimals(dogs)
  printAnimals(cats)

  //We can also add cats to list of dogs, and get a list of animals in return
  val animals: List[Animal] =  dogs ++ cats
  printAnimals(animals)


  //Co-variance
  class CovariantList[+T]
  //Since class is covaraint, so a list of dogs is essentially a list of animals too
  def doSomething(animals: CovariantList[Animal] ) = println("playing with animals")
  val dogList: CovariantList[Dog] = new CovariantList[Dog]
  //This works because the method requires a co-variant type
  //which means list of dogs is also a list of animals, and hence can be passed to the method
  doSomething(dogList)





  /********************* IN-VARIANCE ***************************/

  //In-variance
  class InvariantList[T]
  //Since class is invariant, so a list of dogs is not a subtype of list of animals (even though dog is subtype of animal)
  def doSomething(animals: InvariantList[Animal] ) = println("playing with animals")
  //Below code WILL NOT COMPILE
  //This is because the type passed to method is In-variant.
//  doSomething(dogList)


  /********************* CONTRA-VARIANCE ***************************/

  //Contra-variance
  class ContraVariantCageForAnimal[-T]

  //Following method takes a contravariant type for the cage parameter
  def putDogInCage(dog: Dog, cage: ContraVariantCageForAnimal[Dog] ) = println(s"putting $dog in the cage")

  //Since class is contra-varaint, so a cage for any animal will be a supertype of cage of a dog
  //So Below will also work
  val cageForAnimal: ContraVariantCageForAnimal[Animal] = new ContraVariantCageForAnimal[Animal]
  putDogInCage(Dog("Tommy"), cageForAnimal)
}
