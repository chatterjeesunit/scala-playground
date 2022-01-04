package com.rockthejvm.scalabeginners.exercises

object GenericLinkedList extends App {
  class Person(val name: String) {
    override def toString: String = this.name
  }

  val emptyList: MyLinkedList[Nothing] = MyEmpty

  val list1: MyLinkedList[Int] = emptyList add 5 add 8 add 9 add 10
  println(list1)

  val list2: MyLinkedList[String] = emptyList add "Hulk" add "Iron Man" add "Captain America"
  println(list2)

  val list3 = emptyList add Person("John Doe") add Person ("Jane Doe")
  println(list3)

  val list4: MyLinkedList[AnyRef] = list3 add "ADDD"
  println(list4)

  val stringSizeTransformer = new Transformer[String, String] {
    override def transform(element: String): String = s"$element(${element.length})"
  }
  println(list2.map(stringSizeTransformer))

  val square = new Transformer[Int, Int] {
    override def transform(element: Int): Int = element * element
  }
  println(list1.map(square))

  val even = new Predicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }

  val odd = new Predicate[Int] {
    override def test(element: Int): Boolean = element % 2 != 0
  }

  println(list1.map(square).filter(even))
  println(list1.map(square).filter(odd))

  val flatMapTransformer = new Transformer[Int, MyLinkedList[Int]] {
    override def transform(element: Int): MyLinkedList[Int] = new MyCons[Int](element, new MyCons[Int](element+1, MyEmpty))
  }
  println(list1.flatMap(flatMapTransformer))

}

sealed trait MyLinkedList[+T] {
  def head: T
  def tail: MyLinkedList[T]
  def isEmpty: Boolean

  def add[U >: T](data: U): MyLinkedList[U]
  def filter(predicate: Predicate[T]): MyLinkedList[T]
  def map[U](transformer: Transformer[T, U]): MyLinkedList[U]
  def ++[U >: T](list: MyLinkedList[U]): MyLinkedList[U]
  def flatMap[U](transformer: Transformer[T, MyLinkedList[U]]): MyLinkedList[U]
}

case object MyEmpty extends MyLinkedList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty = true
  override def toString: String = "Nil"

  def add[U >: Nothing](element: U): MyLinkedList[U] = new MyCons(element, MyEmpty)
  def filter(predicate: Predicate[Nothing]): MyLinkedList[Nothing] = MyEmpty
  def map[U](transformer: Transformer[Nothing, U]): MyLinkedList[U] = MyEmpty
  def ++[U >: Nothing](list: MyLinkedList[U]): MyLinkedList[U] = list
  def flatMap[U](transformer: Transformer[Nothing, MyLinkedList[U]]): MyLinkedList[U] = MyEmpty
}

case class MyCons[+T](head: T, tail: MyLinkedList[T]) extends MyLinkedList[T] {
  def isEmpty = false
  override def toString: String = s"${this.head} -> ${this.tail}"

  def add[U >: T](element: U): MyLinkedList[U] = new MyCons(element, this)
  def filter(predicate: Predicate[T]): MyLinkedList[T] = {
    if(predicate.test(head)){
      new MyCons[T](head, tail.filter(predicate))
    }else {
      tail.filter(predicate)
    }
  }
  def map[U](transformer: Transformer[T, U]): MyLinkedList[U] = new MyCons[U](transformer.transform(head), tail.map(transformer))
  def ++[U >: T](list: MyLinkedList[U]): MyLinkedList[U] = new MyCons[U](head, tail ++ list)

  def flatMap[U](transformer: Transformer[T, MyLinkedList[U]]): MyLinkedList[U] =
    transformer.transform(head) ++ tail.flatMap(transformer)
}