package com.hackerrank.practice.functionalprogramming.introduction

/**
  * Created by sunitc on 12/26/16.
  */
object ListLength {

  def f(arr:List[Int]):Int = {

    def count(list:List[Int], sum:Int):Int = {
      list match {
        case List() =>  sum
        case x:: List() => sum + 1
        case x:: xs => count(xs, sum + 1)
      }
    }

    count(arr, 0)
  }
}
