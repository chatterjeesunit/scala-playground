package com.learn.udemyrockjvm.lectures.part2

object AccessModifiers extends App{

  class Parent {

    private def testOne(): String = "Parent :: Private"
    protected def testTwo(): String = "Parent :: Protected"
    def print() = println(s"${testOne()}\n${testTwo()}")
  }

  class Child extends Parent {
    override protected def testTwo(): String = "Child: Protected"
    override def print() = println(s"${testTwo()}")

    //cannot override or access private fields/methods of parent class
    //So cannot override a testOne function
  }

  val parent: Parent = new Parent()
  val child: Parent = new Child()

  //Here we CANNOT access the private/protected members of the class
  parent.print()
  child.print()

}
