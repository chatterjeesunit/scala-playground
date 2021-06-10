case class Person(name:String, age:Int)
val persons = List(Person("Sunit", 35), Person("Sonal", 31), Person("Avanti", 36), Person("Surya", 26))



implicit val ord:Ordering[Int] = Ordering.Int.reverse
persons.sortBy(_.age )

persons.sortWith{ case (p1, p2) => p1.age < p2.age}

//persons.sorted

