package com.hackerrank.practice.algorithms.implementation

import scala.util.{Failure, Success, Try}

/**
  * Created by sunitc on 12/26/16.
  */
object DesignerPDFViewer {

  def main(args: Array[String]): Unit = {

    Try {
      val heights:List[Int] = scala.io.StdIn.readLine.split(" ").take(26).toList.map(_.toInt)

      val word: String = scala.io.StdIn.readLine.split(" ").head

      word.length * word.map(c => heights(c - 'a')).max
    } match {
      case Success(res) => println(res)
      case Failure(f) => println(0)
    }
  }

}
