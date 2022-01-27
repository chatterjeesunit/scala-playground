package com.learn.udemyrockjvm.exercises

import scala.annotation.tailrec

sealed trait GenericSet[A] extends (A => Boolean ) {
  val size: Int
  def head: A
  def tail: GenericSet[A]
  def isEmpty: Boolean
  def apply(x: A): Boolean = contains(x)
  def contains(x: A): Boolean
  def +(x: A): GenericSet[A]
  def ++(anotherSet: GenericSet[A]): GenericSet[A]
  def map[B](fn: A => B): GenericSet[B]
  def flatMap[B](fn: A => GenericSet[B]): GenericSet[B]
  def filter(predicate: A => Boolean): GenericSet[A]
  def foreach(fn: A => Unit): Unit
}

class NilSet[A]() extends GenericSet[A] {
  val size: Int = 0
  def head: A = throw new NoSuchElementException()
  def tail: GenericSet[A] = throw new NoSuchElementException()
  def isEmpty: Boolean = true
  def contains(x: A): Boolean = false
  def +(x: A): GenericSet[A] = ConsSet(head = x, this)
  def ++(anotherSet: GenericSet[A]): GenericSet[A] = anotherSet
  def map[B](fn: A => B): GenericSet[B] = NilSet[B]()
  def flatMap[B](fn: A => GenericSet[B]): GenericSet[B] = NilSet[B]()
  def filter(predicate: A => Boolean): GenericSet[A] = this
  def foreach(fn: A => Unit): Unit = ()
}

class ConsSet[A](val head: A, val tail: GenericSet[A]) extends GenericSet[A] {
  val size: Int = tail.size + 1
  
  def isEmpty: Boolean = false

  def contains(x: A): Boolean = if (head equals x) true else tail contains x

  def +(x: A): GenericSet[A] = if (this contains x) this else ConsSet(x, this)

  def ++(anotherSet: GenericSet[A]): GenericSet[A] = tail ++ anotherSet + head

  def map[B](fn: A => B): GenericSet[B] = (tail map fn) + fn(head)

  def flatMap[B](fn: A => GenericSet[B]): GenericSet[B] = fn(head) ++ (tail flatMap fn)

  def filter(predicate: A => Boolean): GenericSet[A] =
      if(predicate(head)) ConsSet(head, tail filter predicate)
      else tail filter predicate

  def foreach(fn: A => Unit): Unit = {
    fn(head)
    tail foreach fn
  }
}

object GenericSet {
  def apply[A](values: A*): GenericSet[A] = {

    @tailrec
    def recursiveBuildSet(valSeq: Seq[A], acc: GenericSet[A]): GenericSet[A] = {
      if(valSeq.isEmpty) acc
      else recursiveBuildSet(valSeq.tail, acc + valSeq.head)
    }

    recursiveBuildSet(values.toSeq, NilSet())
  }
}