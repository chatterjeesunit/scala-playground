package com.rockthejvm.scalabeginners.lectures.part3

object CollectionsMaps extends App {

  // creating a Map - by passing a sequence of Tuple2
  val m1: Map[String, Int] = Map(  ("Sam", 223),  ("Julia", 3242) , ("John Doe", 23432))

  // creating the same map again using the -> operator, instead of using a tuple
  val m2: Map[String, Int] =  Map(  "Sam" -> 223,  "Julia" -> 3242 , "John Doe" -> 23432)

  // adding elements to an existing map
  val m3: Map[String, Int] = m2 + ("Neo" -> 5555)


  // We can create map of other complex objects also
  // e.g following is a Map of personname and List of address a person has
  case class Address(streetAddress: String, city: String, state:String, country: String)

  val personAddressMap: Map[String, List[Address]] =
    Map(
      "John" ->
        List(
          Address("Travesía Aragón", "Téllez del Penedès", "Andulusia", "Spain"),
          Address("Avinguda Nazario", "L'Aparicio", "Bilzen", "Spain")
        ),
      "Keira" ->
        List( Address("2912 Zboncak Wells", "South Malvina", "CA", "USA"))
    )




  // Fetching particular value from Map
  // Option 1 - using apply method - throws Exception if key is not found in map
  personAddressMap("John")
  // following will throw a java.util.NoSuchElementException: key not found
  personAddressMap("john")

  // Another way to get value from Map without throwing an Exception,
  // is to use method get, which returns an Option
  val phoneNumber: Option[Int] = m2.get("Sam")  // will return Some(223)
  val samPhone: Option[Int] = m2.get("sam")     // will return None
  samPhone.isEmpty // will return true

  // following method is used to get all keys from the map
  val personNames: Set[String] = personAddressMap.keySet  // Set(John, Keira)

  // We can also check first if elements exists in a map or not using `contains` method
  m2.contains("Sam") // returns true
  m2.contains("sam") // returns false

  // Updating a value for map
  // will create a new map
  m2.updated("Sam", 99999)


  // Converting map to a list - can be done using .toList method
  // will convert it to a list of Tuple2
  val list: List[(String, Int)] = m2.toList // List((Sam,223), (Julia,3242), (John Doe,23432))

  // List of tuples can be easily converted to a map using .map function
  val l1: List[(String, Int)] = List(("A",1), ("B",2))
  val m4: Map[String, Int] = l1.toMap

  // normal lists can also be converted to Map
  val fruits: List[String] = List("apple", "banana", "pineapple")
  //following code will convert list `fruits` to a map - Map(apple -> 5, banana -> 6, pineapple -> 9)
  fruits.map(f => (f, f.length)) //convert each list item to first a Tuple2
    .toMap  // then call .toMap on list of tuples


  // We can also do some grouping if list items are not unique
  case class Person(name: String, age: Int, dept: String)
  val persons: List[Person] = List(
    Person("John", 35 , "Sales"), Person("Kiara", 25 , "Sales"),
    Person("Neo", 18 , "Marketing"), Person("Angelina", 25 , "IT"),
    Person("Sam", 43 , "IT"))

  // Following code will group employes by their department
  // will create a Map[String, List[Person]]
  val personsByDept: Map[String, List[Person]] = persons.groupBy(_.dept)


  // We can modify all values in the map also using .mapValues function
  // e.g in above map we only want personNames in each dept and not list of persons
  // This will convert it to a Map[String, List[String]]
  // will create following map
  // Map(Sales -> List(John, Kiara), Marketing -> List(Neo), IT -> List(Angelina, Sam))
  personsByDept
    .view  // convert to a MapView first
    .mapValues(list => list.map(_.name))  // modify the values
    .toMap // convert MapView to Map again

  // If we have to modify both keys and values, then we can use the `.map` method
  // e.g Key - change it to upper case, and Value - make it a list of person name
  // this will create following map
  // Map(SALES -> List(John, Kiara), IT -> List(Angelina, Sam), MARKETING -> List(Neo))
  personsByDept
    .map(m => m._1.toUpperCase -> m._2.map(_.name))


}
