package com.hackerrank.practice.algorithms.implementation

/**
  * Created by sunitc on 7/15/17.
  */
object CircularRotation {
  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var k = sc.nextInt();
    var q = sc.nextInt();
    var a = new Array[Int](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }
    val r: Int = k % n
    var a0: Int = 0;
    while(a0 < q){
      var m = sc.nextInt();
      val pos: Int = ((m - r + n) % n)
      //println(s"m=$m, r=$r, pos=$pos")
      println(a(pos))
      a0+=1;
    }


  }
}
