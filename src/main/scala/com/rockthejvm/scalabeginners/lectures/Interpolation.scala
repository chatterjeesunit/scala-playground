package com.rockthejvm.scalabeginners.lectures

object Interpolation extends App{

  val item:String = "apple"
  val price: Double = 25.5
  val quantity: Int = 9

  val costMessage = "Price of " + quantity + " " + item + "s, is " + quantity*price + " EUR"
  println(costMessage)

  val costMessage2 = s"Price of ${quantity} ${item}s, is ${quantity * price} EUR"
  println(costMessage2)

  val costMessage3 = s"Price of ${quantity} ${item}s, is ${quantity * price} $$"
  println(costMessage3)

  val costMesssage4 = f"Price of ${quantity} ${item}s, is ${quantity * price}%1.2f $$"
  println(costMesssage4)


  val messageWithNewLine = "This is first line.\nThis is second line."
  val rawMessageWithNewLine = raw"This is first line.\nThis is second line."

  println(messageWithNewLine)
  println(rawMessageWithNewLine)

  val longString1: String = "{\n " +
    "\"name\": \"John Doe\",\n " +
    "\"age\" : 10\n" +
    "}"
  println (longString1)


  val longString2: String = """{
                              "name": "John Doe",
                              "age" : 10
                              }"""
  println(longString2)

  val longString3: String = """{
                              | "name": "John Doe",
                              | "age" : 10
                              |}""".stripMargin

    println (longString3)
}
