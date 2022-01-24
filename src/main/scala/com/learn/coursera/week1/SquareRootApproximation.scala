package com.coursera.week1

import scala.annotation.tailrec

/**
  * Created by sunitc on 29/5/16.
  */
object SquareRootApproximation {

  def abs(x:Double):Double = if(x >=0) x else -x

  def mean(x:Double, y:Double):Double = (x+y)/2


  def sqrt(x:Double):Double = {

    def takeNextGuess(g:Double):Double = mean (g, x/g)

    def isGuessGoodEnough(g:Double):Boolean = if(abs(g*g/x - 1) < 0.00001) true else false

    @tailrec
    def sqrtIter(g:Double):Double = if (isGuessGoodEnough(g)) g else sqrtIter(takeNextGuess(g))

    sqrtIter(1.0)
  }
}
