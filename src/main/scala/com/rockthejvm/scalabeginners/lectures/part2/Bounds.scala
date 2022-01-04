package com.rockthejvm.scalabeginners.lectures.part2

object Bounds extends App {
  def trainDomesticAnimals[A <: DomesticAnimal](animal: A): Unit = {
    println(s"Training $animal")
  }


  val dog: Dog = Dog("Tommy")
  val cat: Cat = Cat("Whiskers")
  val cow: Cow = Cow("Emma")
  val somePet: Pet = new Pet("somePet", "SomePet") {}
  val someDomesticAnimal: DomesticAnimal =
    new DomesticAnimal("dummy", "SomeDomesticAnimal") {}

  // Following animals can be passed on to the method `trainDomesticAnimals`
  // As they are subtypes of `DomesticAnimal`
  trainDomesticAnimals(dog)
  trainDomesticAnimals(cat)
  trainDomesticAnimals(cow)
  trainDomesticAnimals(somePet) //can even pass a pet
  trainDomesticAnimals(someDomesticAnimal)

  val animalDog: Animal = Dog("Tommy")
  val tiger: Tiger = Tiger("sabre")
  val dino: Dinosaur = Dinosaur("Rex")

  // Following will give error, as the method expects only subtypes of DomesticAnimal
//  trainDomesticAnimals(tiger)
//  trainDomesticAnimals(dino)

  // This will also not compile, even though the animal instance is actually a `Dog`
  // This is because, we are passing it as an `Animal` which not within bounded types.
//  trainDomesticAnimals(animalDog)


  class Cage[A >: Pet ](animal: A)


  // We can put dog, cats or even dinosaurs or tigers in the cage
  // As long as their type are `Pet` / `Domestic Animal` / `Animal` - all supertypes of `Pet`
  val cage1: Cage[Pet] = new Cage(dog)
  val cage2: Cage[DomesticAnimal] = new Cage(dog)
  val cage3: Cage[Animal] = new Cage(dog)
  val cage4: Cage[Pet] = new Cage(cat)
  val cage5: Cage[Animal] = new Cage(tiger)
  val cage6: Cage[Animal] = new Cage(dino)

  // However Following code won't compile now.
  // We are again trying put the same animals into cage - dog/cat/dinosaur/tiger
  // But this time this code does not compiles as the cage types are as they are not supertypes of `Pet`
//  val cage7: Cage[Dog] = new Cage(dog)
//  val cage8: Cage[Cat] = new Cage(cat)
//  val cage9: Cage[WildAnimal] = new Cage(tiger)
//  val cage10: Cage[Dinosaur] = new Cage(dino)


  class NewCage[A >: Pet <: DomesticAnimal](animal: A)

  // We can put dogs/cats in the cage
  // As long as their type are not within bounds of `Pet` and  `Domestic Animal`
  val cage11: NewCage[Pet] = new NewCage(dog)
  val cage12: NewCage[DomesticAnimal] = new NewCage(dog)
  val cage13: NewCage[Pet] = new NewCage(cat)

  // Following code won't compile now,
  // As we are trying to put the dog, cat or dinosaur as their own types or as `Animal` types.
  // This will throw error, as these types are not within bounds of `Pet` and  `Domestic Animal`
//  val cage14: NewCage[Dog] = new NewCage(dog)
//  val cage15: NewCage[Cat] = new NewCage(cat)
//  val cage16: NewCage[WildAnimal] = new NewCage(tiger)
//  val cage17: NewCage[Animal] = new NewCage(dino)

}


abstract class Animal(val name: String, val animalType: String) {
  override def toString: String = s"${animalType}(${name})"
}

abstract class DomesticAnimal(name: String, animalType: String) extends Animal(name, animalType)
abstract class WildAnimal(name: String, animalType: String) extends Animal(name, animalType)

abstract class Pet(name: String, animalType: String) extends DomesticAnimal(name, animalType)
abstract class FarmAnimal(name: String, animalType: String) extends DomesticAnimal(name, animalType)

class Dog(name: String) extends Pet(name, "Dog")
class Cat(name: String) extends Pet(name, "Cat")
class Cow(name: String) extends FarmAnimal(name, "Cow")
class Goat(name: String) extends FarmAnimal(name, "Goat")
class Tiger(name: String) extends WildAnimal(name, "Tiger")
class Dinosaur(name: String) extends Animal(name, "Dinosaur")
