package com.coursera.week3.linkedlist

/**
  * Created by sunitc on 12/6/16.
  */
class Cons[T](val head:T, val tail:LinkedList[T]) extends LinkedList[T] {

  override def isEmpty: Boolean = false

  override def addNode(x: T): LinkedList[T] = new Cons[T](head, tail addNode x)

  override def toString:String = s"${head} -> ${tail.toString}"
}
