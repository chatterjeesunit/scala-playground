package com.learn.udemyrockjvm.exercises

abstract class GenericStream[T] {
  def isEmpty: Boolean
  def head: T
  def tail: GenericStream[T]

  def #::[U >: T](element: U): GenericStream[U]
  def ++[U >: T](otherStream: GenericStream[U]): GenericStream[U]

  def foreach(fn: T => Unit): Unit
  def map[U](fn: T => U): GenericStream[U]
  def flatMap[U](fn: T => GenericStream[U]): GenericStream[U]
  def filter(fn: T => Boolean): GenericStream[T]
  def take(n: Int): GenericStream[T]
  def takeAsList(n: Int): List[T]
}

object GenericStream {
  def from[T](element: T): GenericStream[T] = new InfiniteStream[T](element)
}

class EmptyStream[T]() extends GenericStream[T] {
  def isEmpty: Boolean = true
  val head: T  = throw new NoSuchElementException
  lazy val tail: GenericStream[T] = throw new NoSuchElementException

  def #::[U >: T](element: U): GenericStream[U] = GenericStream.from(element)
  def ++[U >: T](otherStream: GenericStream[U]): GenericStream[U] = otherStream

  def foreach(fn: T => Unit): Unit = {}
  def map[U](fn: T => U): GenericStream[U] = new EmptyStream[U]
  def flatMap[U](fn: T => GenericStream[U]): GenericStream[U] = new EmptyStream[U]
  def filter(fn: T => Boolean): GenericStream[T] = new EmptyStream[T]
  def take(n: Int): GenericStream[T] = new EmptyStream[T]
  def takeAsList(n: Int): List[T] = new List[T]
}

class InfiniteStream[T](val head: T, lazy val tail: GenericStream[T]) extends GenericStream[T] {
  def isEmpty: Boolean = false

  def #::[U >: T](element: U): GenericStream[U] = new InfiniteStream[U](element, this)
  def ++[U >: T](otherStream: GenericStream[U]): GenericStream[U] = otherStream

  def foreach(fn: T => Unit): Unit = {}
  def map[U](fn: T => U): GenericStream[U] = new EmptyStream[U]
  def flatMap[U](fn: T => GenericStream[U]): GenericStream[U] = new EmptyStream[U]
  def filter(fn: T => Boolean): GenericStream[T] = new EmptyStream[T]
  def take(n: Int): GenericStream[T] = new EmptyStream[T]
  def takeAsList(n: Int): List[T] = new List[T]
}
