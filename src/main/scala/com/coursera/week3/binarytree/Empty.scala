package com.coursera.week3.binarytree

/**
  * Created by sunitc on 12/6/16.
  */
object Empty extends IntSet {

  override def include(x: Int): IntSet = new BinaryTreeSet(x, Empty, Empty)

  override def contains(x: Int): Boolean = false

  override def toString:String = "."

  override def union(otherSet: IntSet): IntSet = otherSet

  override def filterAcc(p: (Int) => Boolean, acc: IntSet): IntSet = acc

  override def highest: IntSet = throw new NoSuchElementException("No highest for Empty Set")

  override def isEmpty: Boolean = true
}
