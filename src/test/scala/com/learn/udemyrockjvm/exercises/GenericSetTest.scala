package com.learn.udemyrockjvm.exercises

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.*
import matchers.should.*

import java.util.NoSuchElementException
import scala.reflect.ClassTag

class GenericSetTest extends AnyFunSuite with Matchers {

  test("Should be able to create a new GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    assert(testSet.size == 7)

    assert((Seq(1, 2, 3, 4, 5, 6, 7) forall(x => testSet.contains(x))) == true)
  }

  test("Should be able to add value to a GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    assert(testSet.size == 7)

    assert((testSet + 13).size == 8)

    assert((testSet + 13 + 15 + 17).size == 10)
  }



  test("Should not add duplicate element to a GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    assert(testSet.size == 7)

    assert((testSet + 3).size == 7)

    assert((testSet + 3 + 5 + 7).size == 7)
  }


  test("Should be able to concat two GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3) ++ GenericSet(3, 5 , 8, 1, 3, 2, 9)

    assert(testSet.size == 9)

    assert((Seq(1, 2, 3, 4, 5, 6, 7, 8 , 9) forall(x => testSet.contains(x))) == true)
  }

  test("Should be able check if GenericSet contains any elements or not") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    assert((Seq(1, 2, 3, 4, 5, 6, 7) forall(x => testSet.contains(x))) == true)

    assert(testSet.contains(0) == false)
    assert(testSet.contains(8) == false)
    assert(testSet.contains(9) == false)
  }

  test("Should be able to apply map function to a GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3) map ( _ * 10)

    assert(testSet.size == 7)

    assert((Seq(10, 20, 30, 40, 50, 60, 70) forall(x => testSet.contains(x))) == true)
  }

  test("Should be able to flatMap GenericSet") {
    val testSet: GenericSet[GenericSet[Int]] =
      GenericSet( GenericSet(1), GenericSet(5, 3, 6), GenericSet(5, 7, 2), GenericSet(4, 3))

    val resultSet = testSet flatMap (x => x map (_ / 2))

    assert(resultSet.size == 4)

    assert((Seq(0, 1, 2, 3) forall(x => resultSet.contains(x))) == true)

  }

  test("Should be able to filter a GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    val resultSet = testSet filter (_ % 2 == 0)

    assert(resultSet.size == 3)

    assert((Seq(2, 4, 6) forall(x => resultSet.contains(x))) == true)
  }

  test("Should throw exception for head and tail of an empty GenericSet") {
    val testSet: GenericSet[Int] = GenericSet()

    assertThrows[NoSuchElementException] {testSet.head}
    assertThrows[NoSuchElementException] {testSet.tail}
  }

  test("Should return the head and tail of a GenericSet") {
    val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

    assert(testSet.head == 4)
    assert(testSet.tail.head == 2)
    assert(testSet.tail.tail.head == 7)
    assert(testSet.tail.tail.tail.head == 6)
    assert(testSet.tail.tail.tail.tail.head == 3)
    assert(testSet.tail.tail.tail.tail.tail.head == 5)
    assert(testSet.tail.tail.tail.tail.tail.tail.head == 1)
    assert(testSet.tail.tail.tail.tail.tail.tail.tail.size == 0)
  }
}
