package com.coursera.week3.binarytree

/**
  * Created by sunitc on 12/6/16.
  */
class BinaryTreeSet(val elem:Int, val left:IntSet, val right:IntSet) extends IntSet {

  override def include(x: Int): IntSet = if (x < elem) new BinaryTreeSet(elem, left include x, right) else new BinaryTreeSet(elem, left, right include x)

  override def contains(x: Int): Boolean = if (x < elem) left contains x else if (x > elem) right contains x else true

  override def toString:String = s"[${left.toString}${elem}${right.toString}]"

  override def union(otherSet: IntSet): IntSet = (left union right) union otherSet include elem

  override def filterAcc(p: (Int) => Boolean, acc: IntSet): IntSet = {
    val res:IntSet = if(p(elem)) acc.include(elem) else acc
    res union left.filterAcc(p, Empty) union right.filterAcc(p, Empty)
  }


  override def isEmpty: Boolean = false

  override def highest: IntSet = {
    val highSet:IntSet = this.filter(x => x > elem)
    if (highSet.isEmpty) Empty.include(elem) else highSet.highest
  }
}