def sayHi(implicit s:String) = println("Hello " + s + "!")

sayHi("Neo")  //prints -> Hello Neo!

implicit val one = 1  //Define an implicit of different type

sayHi  //Error: could not find implicit value for parameter s: String

implicit val name:String = "John"

sayHi

//implicit val name2:String = "Doe"

