case class Person(name: String, experience:Int, skills: String)

val persons = List(
  Person("Sunit", 10, "Backend Developer, Lead"),
  Person("Surya", 3, "Backend Developer"),
  Person("Avanti Pande", 10, "Backend Developer, Lead"),
  Person("Surya", 3, "Backend Developer"),
  Person("Sunit", 10, "Backend Developer, Lead"),
  Person("Vishwas", 7, "Full stack Developer"),
  Person("Sunit", 10, "Backend Developer, Lead"),
  Person("Avanti Pande", 10, "Backend Developer, Lead"),
  Person("Sunit", 10, "Backend Developer, Lead")
)


persons.groupBy(_.name).mapValues(_.head).values.toList

persons.groupBy(_.name).map{case (name, quoteList) => quoteList.head}

persons.distinct
