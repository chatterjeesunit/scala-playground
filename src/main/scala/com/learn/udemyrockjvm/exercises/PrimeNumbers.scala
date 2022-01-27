package com.learn.udemyrockjvm.exercises

import scala.annotation.tailrec

object PrimeNumbers extends App {

  def isPrime(n: Long): Boolean = {

    def sqrt(n:Long): Long = Math.sqrt(n).toLong

    @tailrec
    def checkPrime(divisor: Long): Boolean = {
      if(divisor == 1) true
      else if(n % divisor == 0) false
      else checkPrime(divisor - 1)
    }

    checkPrime(sqrt(n))
  }

  println(s"isPrime(5) = ${isPrime(5)}")
  println(s"isPrime(19) = ${isPrime(19)}")
  println(s"isPrime(51) = ${isPrime(51)}")
  println(s"isPrime(89) = ${isPrime(89)}")
  println(s"isPrime(97) = ${isPrime(97)}")
  println(s"isPrime(117) = ${isPrime(117)}")
  println(s"isPrime(659) = ${isPrime(659)}")
  println(s"isPrime(997) = ${isPrime(997)}")
  println(s"isPrime(2003) = ${isPrime(2003)}")
  println(s"isPrime(9973) = ${isPrime(9973)}")

}
