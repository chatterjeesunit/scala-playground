package com.learn.udemyrockjvm.lectures.part3

object CollectionMapFunction extends App {

  val salaries = List(1000, 1222, 2311, 521, 2324, 3422)

  // doubles the salaries
  // Returns a list - List(2000, 2444, 4622, 1042, 4648, 6844)
  val doubleSalaries = salaries.map(_ * 2)



  case class ExamScore(student: String, marks: Int)

  val scores:List[ExamScore] = List(
    ExamScore("Iron Man", 45), ExamScore("Hulk", 72), ExamScore("Black Widow", 11),
    ExamScore("Captain America", 54), ExamScore("Doctor Strange", 83),
    ExamScore("Spiderman", 38), ExamScore("HawkEye", 35), ExamScore("Antman", 90)
  )

  // Get name of all students
  // Convert the list from List[ExamScores] to a List[String]
  // Should return following output
  // List(Iron Man, Hulk, Black Widow, Captain America,
  //    Doctor Strange, Spiderman, HawkEye, Antman)
  val students: List[String] = scores.map(_.student)


  // Map and filter to-gether
  // Get Names of all students who have failed
  // Should return - List(Black Widow, Spiderman, HawkEye)
  val failedStudents: List[String] =
  scores
    .filter(_.marks < 40)  // filters Exam scores where marks < 40
    .map(_.student) // Extracts student names

}
