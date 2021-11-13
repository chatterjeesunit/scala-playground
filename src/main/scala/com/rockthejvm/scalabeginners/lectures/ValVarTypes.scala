package com.rockthejvm.scalabeginners.lectures

object ValVarTypes extends App {

  //Val - immutable
  val num: Int = 555

  // type is inferred automatically
  val text = "test"

  //Variables - mutable
  var flag = true
  println(flag)
  flag = false
  println(flag)

}
