package com.hackerrank.challenges.codeweek35

import scala.collection.mutable

/**
  * Created by sunitc on 11/18/17.
  */
/*
1
1 3
=> 1

1
4 2
=> 13

1
1000000009 1000
=> 1000000008
*/
object HighwayConstruction {
    private val mod: Long = 1000000009

    def highwayConstruction(n: Long, k: Int): Int =  {
      var finalSum: Long = 0
      var nCounter: Long = 2
      while(nCounter < n)
      {
        var moduloMap: mutable.Map[Int, Long] = new mutable.HashMap[Int, Long]()
        var counter: Int = 1
        while(counter <= k) {
          counter match {
            case 1 => moduloMap.put(counter, nCounter % mod)
            case 2 => moduloMap.put(counter, (nCounter*nCounter) % mod)
            case _ => {
              val oldVal:Long = moduloMap.get(counter/2).get
              moduloMap.put(counter, (oldVal*oldVal) % mod)
            }
          }
          counter *= 2
        }
        var remainder: Int = k
        var sum: Long = 1
        while(remainder > 0 && counter >= 1) {
          if(counter <= remainder) {
            sum *= moduloMap.get(counter).get
            remainder -= counter
          }
          counter /= 2
        }
        finalSum += sum
        nCounter += 1

      }
      (finalSum % mod).toInt

    }


    def main(args: Array[String]):Unit = {
      val sc = new java.util.Scanner (System.in);
      var q = sc.nextInt();
      var a0 = 0;
      while(a0 < q){
        var n = sc.nextLong();
        var k = sc.nextInt();
        val result = highwayConstruction(n, k);
        println(result)
        a0+=1;
      }
    }

}
