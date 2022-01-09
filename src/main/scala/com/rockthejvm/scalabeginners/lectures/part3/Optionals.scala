package com.rockthejvm.scalabeginners.lectures.part3

import scala.util.Random

object Optionals extends App {

  import scala.util.Random

  def getHost(randomBool: Boolean): Option[String] = {
    if(randomBool) Some(Random().nextString(10)) else None
  }
  def getPort(randomBool: Boolean): Option[Int] = {
    if(randomBool) Some(Random().nextInt(1000)) else None
  }

  case class Connection(host: String, port: Int) {
    def getStatus(): String =  s"Established a connection from $host:$port"
  }

  def createConnection(host: String, port:Int, randomBool: Boolean): Option[Connection] = {
    if(randomBool) Some(Connection(host, port)) else None
  }

  val randomBoolean = Random().nextBoolean()

  // Option 1 : get a connection status using flatmp, map
  val connectionResult: String =
    getHost(randomBoolean)
    .flatMap(h =>
      getPort(randomBoolean)
        .flatMap(p =>
          createConnection(h, p, randomBoolean)
            .map(c =>
              c.getStatus()
            )
        )
    ).getOrElse(s"Could not establish connection")


  // Option 2 : using for comprehension
  val result = for {
    h <- getHost(randomBoolean)
    p <- getPort(randomBoolean)
    c <- createConnection(h, p, randomBoolean)
  } yield c.getStatus()

  val connectionResultNew: String = result.getOrElse(s"Could not establish connection")

  println(connectionResult)
  println(connectionResultNew)
}
