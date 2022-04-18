case class Person(var personId: String, name: String, var email: String)

val p1: Person = Person("007", "James Bond", "bond@mi6.com")
var p2: Person = Person("007", "James Bond", "bond@mi6.com")

println(p1.equals(p2))

p2.email = "bond@mi6.uk"

println(p1.equals(p2))

println (p1)


