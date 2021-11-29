package com.rockthejvm.scalabeginners.lectures.part2

import scala.language.postfixOps

object RationalDemo extends App{

  val r1 = Rational(5, 7)
  val r2 = Rational(4, 3)
  val sum: Rational = r1 + r2
  println(s"$r1 + $r2 = $sum")

  val sum1 = r1.add(r2)  //Normal way of calling methods

  val sum2 = r1 add r2   //Infix notation - syntactic sugar

  val negR1 = -r1
  println(negR1)

  val result = -(r1 invert)

  println(result)

}

class Rational(val numerator: Int , val denominator: Int) {

  def add (other: Rational): Rational =
    Rational(
      this.numerator * other.denominator + this.denominator * other.numerator,
      this.denominator * other.denominator)

  def + (other: Rational): Rational =
    Rational(
      this.numerator * other.denominator + this.denominator * other.numerator,
      this.denominator * other.denominator)

  override def toString = s"$numerator/$denominator"

  def unary_- : Rational = Rational(-numerator, denominator)

  def invert: Rational = Rational(denominator, numerator)

}