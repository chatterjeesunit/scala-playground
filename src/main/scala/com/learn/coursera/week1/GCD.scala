package com.coursera.week1

import scala.annotation.tailrec

/**
  * Created by sunitc on 29/5/16.
  */
object GCD {

  @tailrec
  def gcd(x:Int, y:Int) :Int = if (x%y == 0 ) y else gcd (y, x%y)

}
