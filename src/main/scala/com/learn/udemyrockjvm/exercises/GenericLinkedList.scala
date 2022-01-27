package com.learn.udemyrockjvm.exercises

import scala.runtime.Nothing$

sealed trait MyLinkedList[+T] {
  def head: T
  def tail: MyLinkedList[T]
  def isEmpty: Boolean

  def add[U >: T](data: U): MyLinkedList[U]
  def filter(predicate: T => Boolean): MyLinkedList[T]
  def map[U](mapper: T => U): MyLinkedList[U]
  def ++[U >: T](list: MyLinkedList[U]): MyLinkedList[U]
  def flatMap[U](mapper: T => MyLinkedList[U]): MyLinkedList[U]
  def foreach(fn: T => Unit): Unit
  def sort(fn: (T, T) => Int): MyLinkedList[T]
  def fold[U](start: U)(fn: (U, T) => U): U
  def zipWith[U, V](list: MyLinkedList[U], fn: (U, T) => V): MyLinkedList[V]
}

case object MyEmpty extends MyLinkedList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty = true
  override def toString: String = "Nil"

  def add[U >: Nothing](element: U): MyLinkedList[U] = new MyCons(element, MyEmpty)
  def filter(predicate: Nothing => Boolean): MyLinkedList[Nothing] = MyEmpty
  def map[U](mapper: Nothing => U): MyLinkedList[U] = MyEmpty
  def ++[U >: Nothing](list: MyLinkedList[U]): MyLinkedList[U] = list
  def flatMap[U](mapper: Nothing => MyLinkedList[U]): MyLinkedList[U] = MyEmpty
  def foreach(fn: Nothing => Unit): Unit = {}
  def sort(fn: (Nothing, Nothing) => Int): MyLinkedList[Nothing] = MyEmpty
  def fold[U](start: U)(fn: (U, Nothing) => U): U = start
  def zipWith[U, V](list: MyLinkedList[U], fn: (U, Nothing) => V): MyLinkedList[V] = MyEmpty
}

case class MyCons[+T](head: T, tail: MyLinkedList[T]) extends MyLinkedList[T] {
  def isEmpty = false
  override def toString: String = s"${this.head} -> ${this.tail}"

  def add[U >: T](element: U): MyLinkedList[U] = new MyCons(element, this)

  def filter(predicate: T => Boolean): MyLinkedList[T] = {
    if (predicate(head)) new MyCons[T](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  def map[U](mapper: T => U): MyLinkedList[U] = new MyCons[U](mapper(head), tail.map(mapper))

  def ++[U >: T](list: MyLinkedList[U]): MyLinkedList[U] = new MyCons[U](head, tail ++ list)

  def flatMap[U](mapper: T => MyLinkedList[U]): MyLinkedList[U] = mapper(head) ++ tail.flatMap(mapper)

  def foreach(fn: T => Unit): Unit = {
    fn(head)
    tail.foreach(fn)
  }

  def sort(fn: (T, T) => Int): MyLinkedList[T] = {
    //Insertion sort
    def insert(valueToInsert: T, sortedList: MyLinkedList[T]): MyLinkedList[T] = {
      if(sortedList.isEmpty) MyCons(valueToInsert, MyEmpty)
      else if(fn(valueToInsert, sortedList.head) <= 0) MyCons(valueToInsert, sortedList)
      else MyCons(sortedList.head, insert(valueToInsert, sortedList.tail))
    }

    val sortedTail = tail.sort(fn)
    insert(head, sortedTail)
  }

  def fold[U](start: U)(fn: (U, T) => U): U = {
    val result:U  = fn(start, head)
    tail.fold(result)(fn)
  }

  def zipWith[U, V](list: MyLinkedList[U], fn: (U, T) => V): MyLinkedList[V] = {
    val newValue:V  = fn(list.head, this.head)
    new MyCons[V](newValue, this.tail.zipWith(list.tail, fn))
  }
}

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

  val stringSizeMapperFn: String => String = value => s"$value(${value.length})"
  println(list2.map(stringSizeMapperFn))

  val squareFn: Int => Int = element => element * element
  println(list1.map(squareFn))

  val evenPredicate: Int => Boolean = element => element % 2 == 0
  val oddPredicate: Int => Boolean = element => element % 2 != 0
  println(list1.map(squareFn).filter(evenPredicate))
  println(list1.map(squareFn).filter(oddPredicate))

  val flatMapTransformer: Int => MyLinkedList[Int] = value => new MyCons[Int](value, new MyCons[Int](value+1, MyEmpty))
  println(list1.flatMap(flatMapTransformer))


  list1.foreach(x => print(s"${x * 10},  "))
  println

  //find result of all string lengths in list
  val strlengthsum = list2.fold(0)((sum, str ) => sum + str.length)
  println(s"String length sum = ${strlengthsum}")

  val anotherList = MyCons(4, MyEmpty) add 3 add 2 add 1
  val zippedList = list1.zipWith(anotherList, (x, y) => x * y)
  println(zippedList)


  val unsortedList = MyCons(8, MyEmpty) add 6 add 1 add 9 add 3
  println(s"Unsorted list = $unsortedList")
  //ascending sort
  println(s"Ascending sort = ${unsortedList.sort((x, y) => x - y)}")
  //descending sort
  println(s"Descending sort = ${unsortedList.sort((x, y) => y - x)}")

}
