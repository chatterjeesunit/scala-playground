
class Animal
class Dog extends Animal

val dogArray: Array[Dog] = Array( new Dog )
//Arrays are in-variant in scala
//This will not compile
val animalArray: Array[Animal] = dogArray


val dogList: List[Dog] = List( new Dog )
//List are co-variant in scala, so this will work in Scala
val animalList: List[Animal] = dogList



