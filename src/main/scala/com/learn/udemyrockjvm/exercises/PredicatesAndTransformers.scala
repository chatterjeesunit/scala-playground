package com.learn.udemyrockjvm.exercises

object PredicatesAndTransformers extends App {

  val square = new Transformer[Int, Int] {
    override def transform(element: Int): Int = element * element
  }

  val cube = new Transformer[Int, Int] {
    override def transform(element: Int): Int = element * element * element
  }

  val stringWithSize = new Transformer[String, String] {
    override def transform(element: String): String = s"$element (${element.length})"
  }

  val even = new Predicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }

  val odd = new Predicate[Int] {
    override def test(element: Int): Boolean = element % 2 != 0
  }

  println(square.transform(5))
  println(cube.transform(5))
  println(stringWithSize.transform("Sunit"))

  println(even.test(5))
  println(odd.test(5))
}

trait Predicate[-T] {
  def test(element: T): Boolean
}

trait Transformer[-A, B] {
  def transform(element: A): B
}

