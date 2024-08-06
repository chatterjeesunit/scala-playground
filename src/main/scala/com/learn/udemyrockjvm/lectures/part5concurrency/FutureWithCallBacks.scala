package com.learn.udemyrockjvm.lectures.part5concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureWithCallBacks extends App {

  def log(msg: String): Unit = System.out.println(s"${Thread.currentThread().getName}: $msg")

  def fetchPrice(stockIdentifier: String): Double = {
    // do some long computation or I/O to fetch the price
    log(s"Fetching price of $stockIdentifier share")

    //simulate it here by doing Thread.sleep
    Thread.sleep(Random.between(3000, 7000))
    Random.nextDouble() * 1000
  }



  log(s"Executing future to fetch apple share price")
  val appleSharePrice: Future[Double] = Future {
    fetchPrice("APPLE")
  }


  appleSharePrice onComplete {
    case Success(price) => log(f"Callback 1: Price of APPLE share = ${price}%1.2f")
    case Failure(ex) => log(s"Exception in fetching share Price for APPLE share = $ex")
  }

  //registering multiple callbacks on same future
  appleSharePrice onComplete {
    case Success(price) => log(f"Callback 2: Price of APPLE share = ${price}%1.2f")
    case Failure(ex) => log(s"Exception in fetching share Price for APPLE share = $ex")
  }

  //registering multiple callbacks on same future
  appleSharePrice.foreach(price => log(f"Share Price = ${price}%1.3f"))

  //registering multiple callbacks on same future
  appleSharePrice.failed.foreach(ex => log(ex.toString))


  log("Continuing with execution of main thread")

  //waiting for completion of future
  while (!appleSharePrice.isCompleted) {
    log("waiting for future completion")
    Thread.sleep(1000)
 }

  log("Main thread exiting now")
}
