package com.hackerrank.challenges

/**
  * Created by sunitc on 4/4/18.
  */
object CloudyDay {

  case class PC(population: Long, cloudIndex: Option[Long])
  case class LP(location: Long, totalPopulation: Long)

  def maximumPeople(p: Array[Long], x: Array[Long], y: Array[Long], r: Array[Long]): Long =  {

    val cityData: Map[Long, PC] = p.zip(x).map(x => (x._1, PC(x._2, None))).toMap

//    println(cityData)
//    val cloudData: Map[Long, LP] =

    1l
  }

  def main(args: Array[String]):Unit = {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var p = new Array[Long](n);
    for(p_i <- 0 to n-1) {
      p(p_i) = sc.nextLong();
    }
    var x = new Array[Long](n);
    for(x_i <- 0 to n-1) {
      x(x_i) = sc.nextLong();
    }
    var m = sc.nextInt();
    var y = new Array[Long](m);
    for(y_i <- 0 to m-1) {
      y(y_i) = sc.nextLong();
    }
    var r = new Array[Long](m);
    for(r_i <- 0 to m-1) {
      r(r_i) = sc.nextLong();
    }
    val result = maximumPeople(p, x, y, r);
    println(result)
  }
}
