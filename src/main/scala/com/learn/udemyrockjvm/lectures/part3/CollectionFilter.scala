package com.learn.udemyrockjvm.lectures.part3

object CollectionFilter extends App {


  val listOfIntegers = List(12, 45, 32, 3, 67, 23, 23, 7, 2, 6, 22)

  val isEven: Int => Boolean = _ % 2 == 0
  //Filter even numbers
  listOfIntegers.filter(isEven) //Result =  List(12, 32, 2, 6, 22)

  //Don't filter even numbers
  listOfIntegers.filterNot(isEven) //Result = List(45, 3, 67, 23, 23, 7)




  case class ExamScore(student: String, marks: Int)

  val scores:List[ExamScore] = List(
    ExamScore("Iron Man", 45), ExamScore("Hulk", 72), ExamScore("Black Widow", 11),
    ExamScore("Captain America", 54), ExamScore("Doctor Strange", 83),
    ExamScore("Spiderman", 38), ExamScore("HawkEye", 35), ExamScore("Antman", 90)
  )


  // All students who have failed - less than 40 marks
  // Should return list with following students
  // - List(ExamScore(Black Widow,11), ExamScore(Spiderman,38), ExamScore(HawkEye,35))
  scores.filter(_.marks < 40)

  //All students who have scored more than 80 marks
  // Should return list with following students
  // - List(ExamScore(Doctor Strange,83), ExamScore(Antman,90))
  scores.filter(_.marks >= 80)



}
