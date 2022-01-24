package com.learn.udemyrockjvm.lectures.part3

object CollectionForComprehension extends App {

//  Example 1: For-comprehension for a map function
  val salaries = List(1000, 1222, 2311, 521, 2324, 3422)
  val doubleSalaries = for(s <- salaries) yield s * 2


//  Example 2: For-comprehension for a filter function
  val salaryMoreThan2K = for(
    s <- salaries if s > 2000
  ) yield s


//  Example 3: For-comprehension for filter and map functions
  case class ExamScore(student: String, marks: Int)

  val scores:List[ExamScore] = List(
    ExamScore("Iron Man", 45), ExamScore("Hulk", 72), ExamScore("Black Widow", 11),
    ExamScore("Captain America", 54), ExamScore("Doctor Strange", 83),
    ExamScore("Spiderman", 38), ExamScore("HawkEye", 35), ExamScore("Antman", 90)
  )

  // Get list of names of students who have failed (less than 40 marks)
  val failedStudents: List[String] =
    for(
      s <- scores if s.marks < 40
    ) yield s.student




//  Example 4: For comprehension for flatMap, map and filter

  case class Person(name: String, experience: Int)
  case class Language(name: String, isJVMLanguage: Boolean)
  case class Location(name: String, country: String)
  case class WorkInfo(name: String, skill: String, location: String)

  val persons = List ( Person("John", 10),  Person("Derek", 4),  Person("Sam", 8))
  val languages = List ( Language("Scala", true),  Language("Golang", false) )
  val locations = List ( Location("Pune", "India"),  Location ("Malaga", "Spain"))

  // We want to combine person, skills and locations
  // Filter persons with experience > 5 yrs, locations in Spain, and JVM languages
  // Combine the results into a WorkInfo class - which only contains names of person/language/location
  // Expected result = List(  WorkInfo(John,Scala,Malaga), WorkInfo(Sam,Scala,Malaga))


  //To do this using map , filter and flapMap, we would have written code like this below
  val workInfoList: List[WorkInfo] =
    persons
      .filter(_.experience > 5)  // filter person with experience > 5 year
      .flatMap(person =>
        languages
          .filter(_.isJVMLanguage)  // filter JVM languages
          .flatMap(lang =>
            locations
              .filter(_.country.equals("Spain"))  // filter Spain locations
              .map(loc =>
                WorkInfo(person.name, lang.name, loc.name)
              )))


  // Same results using for-comprehensions
  val workInfoListNew: List[WorkInfo] =
    for {
      p <- persons if p.experience > 5
      l <- languages if l.isJVMLanguage
      loc <- locations if loc.country.equals("Spain")
    } yield WorkInfo( p.name, l.name, loc.name)



}
