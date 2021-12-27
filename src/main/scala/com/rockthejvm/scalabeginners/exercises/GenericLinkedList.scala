package com.rockthejvm.scalabeginners.exercises

object GenericLinkedList extends App {

  trait LinkedList[T] {
    def head: T
    def tail: LinkedList[T]
    def isEmpty: Boolean
    def add(data: T): LinkedList[T]
  }

  class Empty[T] extends LinkedList[T] {
    def head = throw new NoSuchElementException
    def tail = throw new NoSuchElementException
    def isEmpty = true
    def add(data: T) = Cons(data, Empty())
    override def toString: String = "Nil"
  }

  class Cons[T](val head: T, val tail: LinkedList[T]) extends LinkedList[T] {
    def isEmpty = false
    def add(data: T) = Cons(data, this)
    override def toString: String = s"${this.head} -> ${this.tail}"
  }

  class Person(val name: String) {
    override def toString: String = this.name
  }


  val list1: LinkedList[Integer] = Empty[Integer]() add 5 add 8 add 9 add 10
  println(list1)

  val list2: LinkedList[String] = Empty[String]() add "A" add "B" add "C"
  println(list2)

  val list3 = Empty[Person] add Person("John Doe") add Person ("Jane Doe")
  println(list3)
}
