package com.coursera.week3.binarytree

/**
  * Created by sunitc on 12/6/16.
  */
trait IntSet {

  def include(x:Int):IntSet
  def contains(x:Int):Boolean
  def union(otherSet:IntSet):IntSet

  def isEmpty:Boolean

  def filter(p:Int => Boolean) = filterAcc(p, Empty)
  def filterAcc(p:Int => Boolean, acc:IntSet):IntSet

  def highest:IntSet
}
