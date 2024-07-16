package com.learn.udemyrockjvm.exercises

import scala.annotation.tailrec

abstract class GenericStream[+T] {
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
  def takeAsList(n: Int): List[T] = take(n).toList()


  @tailrec
  final def toList[U >: T](acc: List[U] = Nil): List[U] = {
    if(isEmpty) acc
    else tail.toList(head :: acc)
  }
}

object EmptyStream extends GenericStream[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing  = throw new NoSuchElementException
  def tail: GenericStream[Nothing] = throw new NoSuchElementException

  def #::[U >: Nothing](element: U): GenericStream[U] = new InfiniteStream[U](element, this)
  def ++[U >: Nothing](otherStream: GenericStream[U]): GenericStream[U] = otherStream

  def foreach(fn: Nothing => Unit): Unit = ()
  def map[U](fn: Nothing => U): GenericStream[U] = this
  def flatMap[U](fn: Nothing => GenericStream[U]): GenericStream[U] = this
  def filter(fn: Nothing => Boolean): GenericStream[Nothing] = this
  def take(n: Int): GenericStream[Nothing] = this
}

class InfiniteStream[+T](hd: T, t: => GenericStream[T]) extends GenericStream[T] {
  def isEmpty: Boolean = false

  override val head: T = hd
  override lazy val tail: GenericStream[T] = t

  def #::[U >: T](element: U): GenericStream[U] = new InfiniteStream[U](element, this)
  def ++[U >: T](otherStream: GenericStream[U]): GenericStream[U] = new InfiniteStream[U](head, tail ++ otherStream)

  def foreach(fn: T => Unit): Unit = {
    fn(head)
    tail.foreach(fn)
  }
  def map[U](fn: T => U): GenericStream[U] = new InfiniteStream[U]( fn(head), tail.map(fn))
  def flatMap[U](fn: T => GenericStream[U]): GenericStream[U] = fn(head) ++ tail.flatMap(fn)
  def filter(fn: T => Boolean): GenericStream[T] = if(fn(head)) new InfiniteStream[T](head, tail.filter(fn)) else tail.filter(fn)
  def take(n: Int): GenericStream[T] =
    if(n == 0) EmptyStream
    else if(n == 1) new InfiniteStream[T](head, EmptyStream)
    else new InfiniteStream[T](head, tail.take(n-1))

}

object GenericStream {
  def from[T](element: T): GenericStream[T] = new InfiniteStream[T](element, EmptyStream)
}
