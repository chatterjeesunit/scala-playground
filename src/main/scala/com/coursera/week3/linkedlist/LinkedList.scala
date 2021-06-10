package com.coursera.week3.linkedlist

/**
  * Created by sunitc on 12/6/16.
  */
trait LinkedList[T] {

  def head: T

  def tail: LinkedList[T]

  def isEmpty: Boolean

  def addNode(x: T): LinkedList[T]
}