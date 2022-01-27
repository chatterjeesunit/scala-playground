package com.learn.udemyrockjvm.exercises

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.*
import matchers.should.*
import org.scalatest.funspec.AnyFunSpec

import java.util.NoSuchElementException
import scala.reflect.ClassTag

class GenericSetTest extends AnyFunSpec with Matchers {

  describe("creation of a new Generic Set") {
    it("should be able to create a new GenericSet") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)
      assert(testSet.size == 7)
      assert((Seq(1, 2, 3, 4, 5, 6, 7) forall(x => testSet.contains(x))) == true)
    }

    it("should be able to create a empty GenericSet") {
      val testSet: GenericSet[Int] = GenericSet()
      assert(testSet.size == 0)
    }
  }

  describe("add element to a GenericSet"){

    it("should be abe to add element to an empty set") {
      val testSet: GenericSet[Int] = GenericSet()
      assert(testSet.size == 0)
      val resultSet = testSet + 13

      assert(resultSet.size == 1)
      assert(resultSet.contains(13) == true)
    }

    it("should be able to add multiple values in a chain to a GenericSet") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)
      assert(testSet.size == 7)

      val resultSet = testSet + 13 + 15 + 17

      assert(resultSet.size == 10)
      assert(resultSet.contains(13) == true)
      assert(resultSet.contains(15) == true)
      assert(resultSet.contains(17) == true)
    }

    it("should not add duplicate element to a GenericSet") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)
      assert(testSet.size == 7)
      assert((testSet + 3).size == 7)
      assert((testSet + 3 + 5 + 7).size == 7)
    }
  }

  describe("concatenation of two GenericSets") {

    it("concat of two empty set should return an empty set") {
      val set1: GenericSet[Int] = GenericSet()
      val set2: GenericSet[Int] = GenericSet()

      val concatenatedSet: GenericSet[Int] = set1 ++ set2

      assert(concatenatedSet.size == 0)
    }

    it("concat of empty and non empty set should return the non empty set") {
      val set1: GenericSet[Int] = GenericSet()
      val set2: GenericSet[Int] = GenericSet(2)

      val concatenatedSet: GenericSet[Int] = set1 ++ set2

      assert(concatenatedSet.size == 1)
      assert(concatenatedSet.contains(2) == true)
    }

    it("should be able to concat to non empty sets") {
      val set1: GenericSet[Int] = GenericSet(1, 2)
      val set2: GenericSet[Int] = GenericSet(3, 4)

      val concatenatedSet: GenericSet[Int] = set1 ++ set2

      assert(concatenatedSet.size == 4)
      assert(concatenatedSet.contains(1) == true)
      assert(concatenatedSet.contains(2) == true)
      assert(concatenatedSet.contains(3) == true)
      assert(concatenatedSet.contains(4) == true)
    }

    it("should not duplicate elements during concat of two non empty sets") {
      val set1: GenericSet[Int] = GenericSet(1, 2)
      val set2: GenericSet[Int] = GenericSet(3, 2)

      val concatenatedSet: GenericSet[Int] = set1 ++ set2

      assert(concatenatedSet.size == 3)
      assert(concatenatedSet.contains(1) == true)
      assert(concatenatedSet.contains(2) == true)
      assert(concatenatedSet.contains(3) == true)
    }
  }

  describe("head and tail of Generic Set") {

    it("should throw exception for head and tail of an empty set") {
      val testSet: GenericSet[Int] = GenericSet()

      assertThrows[NoSuchElementException] {testSet.head}
      assertThrows[NoSuchElementException] {testSet.tail}
    }

    it("should return the head and tail of a non empty set") {
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

  describe("contains on a GenericSet") {
    it("should be able check if GenericSet contains any elements or not") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)

      assert((Seq(1, 2, 3, 4, 5, 6, 7) forall(x => testSet.contains(x))) == true)

      assert(testSet.contains(0) == false)
      assert(testSet.contains(8) == false)
      assert(testSet.contains(9) == false)
    }
  }

  describe("map function on a GenericSet") {
    it("should be able to apply map function to a GenericSet") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3) map ( _ * 10)

      assert(testSet.size == 7)

      assert((Seq(10, 20, 30, 40, 50, 60, 70) forall(x => testSet.contains(x))) == true)
    }
  }

  describe("flatmap on a GenericSet") {
    it("should be able to flatMap GenericSet") {
      val testSet: GenericSet[GenericSet[Int]] =
        GenericSet( GenericSet(1), GenericSet(5, 3, 6), GenericSet(5, 7, 2), GenericSet(4, 3))

      val resultSet = testSet flatMap (x => x map (_ / 2))

      assert(resultSet.size == 4)

      assert((Seq(0, 1, 2, 3) forall(x => resultSet.contains(x))) == true)

    }
  }

  describe("filter on a genericSet") {

    it("should be able to filter a GenericSet") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 3, 6, 5, 7, 2, 4, 3)
      val resultSet = testSet filter (_ % 2 == 0)

      assert(resultSet.size == 3)
      assert((Seq(2, 4, 6) forall(x => resultSet.contains(x))) == true)
    }

    it("should return an empty set if all elements are filtered out") {
      val testSet: GenericSet[Int] = GenericSet(1, 5, 7)
      val resultSet = testSet filter (_ % 2 == 0)

      assert(resultSet.size == 0)
      assert(resultSet.isEmpty)
    }
  }

  describe("intersection of GenericSet") {

    it("should return an empty set on intersection of two empty sets") {
      val set1: GenericSet[Int] = GenericSet()
      val set2: GenericSet[Int] = GenericSet()

      val resultSet: GenericSet[Int] = set1 & set2

      assert(resultSet.isEmpty)
    }

    it("should return an empty set on intersection of an empty and non empty set") {
      val set1: GenericSet[Int] = GenericSet(1, 3)
      val set2: GenericSet[Int] = GenericSet()

      val resultSet: GenericSet[Int] = set1 & set2

      assert(resultSet.isEmpty)
    }

    it("should return only common elements on intersection of non empty sets") {
      val set1: GenericSet[Int] = GenericSet(1, 3)
      val set2: GenericSet[Int] = GenericSet(3, 4)

      val resultSet: GenericSet[Int] = set1 & set2

      assert(resultSet.size == 1)
      assert(resultSet.contains(1) == false)
      assert(resultSet.contains(3) == true)
      assert(resultSet.contains(4) == false)
    }

    it("should return an empty set, if no elements are common") {
      val set1: GenericSet[Int] = GenericSet(1, 2)
      val set2: GenericSet[Int] = GenericSet(3, 4)


      val resultSet: GenericSet[Int] = set1 & set2

      assert(resultSet.isEmpty)
    }
  }

  describe("difference of GenericSet") {

    it("should return an empty set on difference of two empty sets") {
      val set1: GenericSet[Int] = GenericSet()
      val set2: GenericSet[Int] = GenericSet()

      val resultSet: GenericSet[Int] = set1 -- set2

      assert(resultSet.isEmpty)
    }

    it("should return the non empty set on difference of an empty and non empty set") {
      val set1: GenericSet[Int] = GenericSet(1, 3)
      val set2: GenericSet[Int] = GenericSet()

      val resultSet: GenericSet[Int] = set1 -- set2

      assert(resultSet.size == 2)
      assert(resultSet.contains(1) == true)
      assert(resultSet.contains(3) == true)
    }

    it("should return only non common elements on difference of non empty sets") {
      val set1: GenericSet[Int] = GenericSet(1, 3)
      val set2: GenericSet[Int] = GenericSet(3, 4)

      val resultSet: GenericSet[Int] = set1 -- set2

      assert(resultSet.size == 2)
      assert(resultSet.contains(1) == true)
      assert(resultSet.contains(3) == false)
      assert(resultSet.contains(4) == true)
    }

    it("should return concatenation of both sets, if no elements are common") {
      val set1: GenericSet[Int] = GenericSet(1, 2)
      val set2: GenericSet[Int] = GenericSet(3, 4)


      val resultSet: GenericSet[Int] = set1 -- set2

      assert(resultSet.size == 4)
      assert(resultSet.contains(1) == true)
      assert(resultSet.contains(2) == true)
      assert(resultSet.contains(3) == true)
      assert(resultSet.contains(4) == true)
    }
  }

  describe("remove element from a GenericSet") {

    it("should return empty set when removing element from an empty set") {
      val testSet: GenericSet[Int] = GenericSet()

      val resultSet: GenericSet[Int] = testSet - 5

      assert(resultSet.isEmpty)
      assert(resultSet.contains(5) == false)
    }

    it("should return an empty set if element removed is the only element in the set") {
      val testSet: GenericSet[Int] = GenericSet(5)

      val resultSet: GenericSet[Int] = testSet - 5

      assert(resultSet.isEmpty == true)
      assert(resultSet.contains(5) == false)
    }

    it("should successfuly remove element from a non empty set") {
      val testSet: GenericSet[Int] = GenericSet(1, 6, 5, 2, 8, 9)

      val resultSet: GenericSet[Int] = testSet - 5

      assert(resultSet.size == 5)
      assert(resultSet.contains(5) == false)
    }
  }
}
