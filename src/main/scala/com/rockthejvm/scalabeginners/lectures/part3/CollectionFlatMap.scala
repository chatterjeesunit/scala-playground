package com.rockthejvm.scalabeginners.lectures.part3

object CollectionFlatMap extends App {



  //Suppose we have a list of comma separated strings.
  //      e.g. - List("aaa,AAA", "bbb", "ccc, CcC")
  // We want result as list with each string
  //      e.g. List("aaa", "AAA", "bbb", "ccc", "CcC")

  val inputList = List("aaa,AAA", "bbb", "ccc, CcC")

  // We will use a map function that splits each string using a comma separator
  // If we just use map function, we will get results as list of another collection and not `List[String]`
  // e.g. Output from below code --> List(Array(aaa, AAA), Array(bbb), Array(ccc, " CcC"))
  val result: List[Array[String]] =
  inputList
    .map(_.split(','))  //Split each element using comma separator

  // In above code we got result as `List[Array[String]],
  // but we can also get `List[List[String]]` using a `toList` function
  // Output from below code --> List(List(aaa, AAA), List(bbb), List(ccc, " CcC"))
  val newResult: List[List[String]] =
  inputList
    .map(_.split(',').toList)  //Split each element using comma separator

  // But after map, the output is not a flattened list.
  // If we want to get a flattened list, we will have to
  // use a `flatten` function after the map function
  // Should return output as - List(aaa, AAA, bbb, ccc, " CcC")
  val flattenedList: List[String] =
  inputList
    .map(_.split(','))
    .flatten


  // Or Other Option is to use a flatMap function
  // which basically combines a `map` and `flatten` into one function
  // Should return output as - List(aaa, AAA, bbb, ccc, " CcC")
  val flattenedListAgain: List[String] =
  inputList
    .flatMap(_.split(','))



  //Combine elements of two lists
  val persons = List ("John", "Derek")
  val skills = List ("Java", "Scala")

  val personSkillSets =
    persons
      .flatMap(person =>
        skills
          .map(skill => s"$person-$skill"))



}
