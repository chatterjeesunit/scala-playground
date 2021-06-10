package com.hackerrank.challenges.codeweek34

/**
  * Created by sunitc on 11/10/17.
  * https://www.hackerrank.com/contests/w34/challenges/once-in-a-tram
  */

/*

Sample Input and Output
532536  ->  532541
532596  ->  532604
532876  ->  532901
165901  ->  165903
169908  ->  169916
100999  ->  101002
600600  ->  601007
555555  ->  555564
654321  ->  654339
 */
object OnceInTram {


  def onceInATram(x: Int, numDigits: Int): String =  {
    var numbers: Array[Int] = new Array[Int](numDigits)
    (numDigits-1 to 0 by -1) foreach(i => {
      val k:Int = ((x % Math.pow(10,i+1).toInt))/ Math.pow(10,i).toInt
//      println(s"i = $i , num = ${(x % Math.pow(10,i+1).toInt)}, div = ${Math.pow(10,i).toInt}, k = $k")
      numbers.update((numDigits - i - 1), k)
    })

    var diff:Int = getDiff(numbers, numDigits)
//    println(s"Number = ${numbers.mkString("")}, Difference = $diff")

    var i: Int = numDigits - 1
    while(diff <= 0 && i > (numDigits/2 -1)){
      increaseToMoreThanZeroDiff(numbers, i, numDigits)
      diff = getDiff(numbers, numDigits)
//      println(s"Number = ${numbers.mkString("")}, Difference = $diff")
      i -= 1
    }

    getNearestNumber(numbers, numDigits, diff)
    return numbers.mkString("")
  }

  def getNearestNumber(a: Array[Int], n:Int, diff: Int) = {
    var newDiff: Int  = diff
    var i: Int = n-1
    while(i > (n/2)-1 && newDiff > 0) {
      val cVal: Int = a(i)
      val increment: Int = if( (9-cVal) > newDiff) newDiff else 9-cVal
      newDiff -= increment
      a.update(i, cVal + increment)
      i -= 1
//      println(a.mkString(""))
    }
  }

  def getDiff(a:Array[Int], n: Int):Int = {
    val mid: Int = n / 2
    val (num1:Array[Int], num2:Array[Int]) = a.splitAt(mid)
    num1.sum - num2.sum
  }

  def increaseToMoreThanZeroDiff(a: Array[Int], i:Int, n:Int)= {
    if(a(i) != 0) {
      a.update(i, 0)
      var done: Boolean = false
      var k = i
      while(!done && k > 0) {
        k -= 1
        a.update(k, (a(k) + 1) % 10)
        if(a(k) != 0) done = true

      }
    }
  }


  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var x = sc.nextInt();
    val result = onceInATram(x,6);
    println(result)
  }
}
