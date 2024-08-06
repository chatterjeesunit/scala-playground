package com.learn.udemyrockjvm.lectures.part5concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

object Futures extends App {

  def log(msg: String): Unit = System.out.println(s"${Thread.currentThread().getName}: $msg")

  def fetchPrice(stockIdentifier: String): Double = {
    // do some long computation or I/O to fetch the price
    log(s"Fetching price of $stockIdentifier share")

    //simulate it here by doing Thread.sleep
    Thread.sleep(Random.between(3000, 7000))
    val price = Random.nextDouble() * 1000
    log(s"Fetch price completed for $stockIdentifier")
    price
  }



  log(s"Executing future to fetch apple share price")
  val appleSharePrice: Future[Double] = Future {
    fetchPrice("APPLE")
  }

  log("Continuing with execution of main thread")

  log("Main thread sleeping for 10 seconds")
  Thread.sleep(10 * 1000)

  log("Main thread exiting now")
}
