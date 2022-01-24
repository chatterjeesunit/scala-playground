package com.coursera.week1

/**
  * Created by sunitc on 29/5/16.
  */
object ConditionalExpression {

  def and(x:Boolean , y: => Boolean):Boolean = if (x) y else false

  def or (x:Boolean, y: => Boolean):Boolean = if (x) true else y
}
