package com.coursera.week3.linkedlist

/**
  * Created by sunitc on 12/6/16.
  */
class Nil[T] extends LinkedList[T] {

  override def head: T = throw new NoSuchElementException("Nil.head")

  override def addNode(x: T): LinkedList[T] = new Cons[T](x, new Nil[T])

  override def isEmpty: Boolean = true

  override def tail: LinkedList[T] = throw new NoSuchElementException("Nil.tail")

  override def toString:String = "."
}
