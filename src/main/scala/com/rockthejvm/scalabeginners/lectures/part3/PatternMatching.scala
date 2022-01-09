package com.rockthejvm.scalabeginners.lectures.part3

import scala.util.{Failure, Random, Success, Try}
import scala.util.matching.Regex

object PatternMatching extends App {

  println("***************** Simple switch case *****************")

  def getDayOfWeek(day: Int): String = {

    day match {
      case 1 => "SUNDAY"
      case 2 => "MONDAY"
      case 3 => "TUESDAY"
      case 4 => "WEDNESDAY"
      case 5 => "THURSDAY"
      case 6 => "FRIDAY"
      case 7 => "SATURDAY"
      case _ => "Invalid Day of week"
    }
  }

  println(s"1 = ${getDayOfWeek(1)}")
  println(s"5 = ${getDayOfWeek(5)}")
  println(s"9 = ${getDayOfWeek(9)}")

  println("***************** Combining patterns using | operator *****************")

  def getWorkDay(day: Int): String = {
    day match {
      case 1 | 7 => "Weekend.. Holiday!!"
      case 2 | 3 | 4 | 5 | 6 => "Working day :-("
      case _ => "Invalid Day of week"
    }
  }

  println(s"1 = ${getWorkDay(1)}")
  println(s"5 = ${getWorkDay(5)}")
  println(s"9 = ${getWorkDay(9)}")


  println("***************** Pattern matching with class types *****************")

  trait Device

  case class Phone() extends Device {
    def screenOff(): Unit = println("Turning screen off for mobile")
  }

  case class Laptop() extends Device {
    def turnScreenSaverOn(): Unit = println("Turning screen saver on for the laptop")
  }

  def screenoff(device: Device) = device match {
    case p: Phone => p.screenOff()
    case l: Laptop => l.turnScreenSaverOn()
  }

  screenoff(Phone())
  screenoff(Laptop())


  println("***************** Pattern matching extraction with case classes *****************")

  trait User(name: String)

  case class Employee(employeeName: String, employeeId: Int) extends User(employeeName)

  case class Vendor(vendorName: String, vendorId: Int, org: String) extends User(vendorName)

  val listOfPersons: List[User] = List(
    Employee("John Doe", 234232), Vendor("Kiara", 9872, "TCS"), new User("Dummy") {}
  )

  listOfPersons foreach { user =>
    user match {
      case Employee(name, id) => println(s"found and employee: $name, $id")

      case Vendor(name, _, _) => println(s"Found an vendor: $name")

      case _ => println("found an user")
    }
  }


  println("***************** Guards with pattern matches *****************")

  trait Recipient

  case class Email(email: String) extends Recipient

  case class SMS(phoneNumber: String) extends Recipient

  //black list of emails
  val blackList: Set[String] = Set("blacklist@dummyorg")

  def sendMail(mail: String) = println(s"Sending mail to $mail")

  def sendSMS(number: String) = println(s"Sending sms to $number")


  def sendMessage(target: Recipient): Unit = target match {
    case SMS(phone) => sendSMS(phone)
    case Email(email) if (!blackList.contains(email)) => sendMail(email)
    case _ => println(s"Cannot send message to to $target")
  }

  sendMessage(Email("abc@google.com"))
  sendMessage(Email("blacklist@dummyorg"))
  sendMessage(SMS("+1 3423432"))


  println("***************** pattern match with options *****************")

  def checkIfExists(value: Option[String]) = value match {
    case Some(v) => println(s"found value $v")
    case _ => println("None found")
  }

  checkIfExists(Some("dummy"))
  checkIfExists(None)


  println("***************** pattern matching with tuples *****************")

  def grantWriteAccess(permission: String, isSuperAdmin: Boolean) =
    (permission, isSuperAdmin) match {
      case (_, true) => println("granted write access for super admin")
      case ("WRITE", _) => println("granted write access for normal user")
      case _ => println("access denied")
    }


  grantWriteAccess("READ", true)
  grantWriteAccess("WRITE", false)
  grantWriteAccess("VIEW", false)




  println("***************** pattern match with lists *****************")

  //Method to get the last element of the list
  def getLastElement[T] (list: List[T]): Option[T] = list match {
    case List() => None   // Empty list
    case x :: Nil => Some(x)  // List with only 1 element
    case head :: tail => getLastElement(tail) // list with a head and a tail list.
  }

  println(s"Last element = ${getLastElement(List())}")
  println(s"Last element = ${getLastElement(List(5))}")
  println(s"Last element = ${getLastElement(List(5, 3, 2, 11 , 9))}")


  // Merge two sorted lists
  def merge(firstList:List[Int], secondList: List[Int]) : List[Int] =
    (firstList, secondList) match {
      case (xs, List()) => xs   // if second list is empty, return first list
      case (List() , ys ) => ys // if first list is empty, return second list
      case (xHead :: xTail, yHead::yTail) =>
        if(xHead <= yHead) xHead :: merge(xTail, secondList)
        else yHead :: merge (firstList, yTail)
  }


  println(s"Merge result = ${merge(List ( 1, 6, 8), List( 2, 5, 6))}")


  println("***************** pattern match with streams *****************")

  def sumStream(streamOfInts: Stream[Int]):Int = streamOfInts match {
    case head #:: tail => head + sumStream(tail)
    case _ => 0
  }

  println(sumStream((5 to 8).toStream))
  println(sumStream((1 to 1).toStream))
  println(sumStream(Stream.empty))



  println("***************** regex pattern match *****************")


  // Page for a CityPage. Sample URL = https://www.wealthminder.com/financial-advisors-santa-monica-CA
  // Regex Pattern for it is below
  // In this pattern we are extracting groups for "host", "city" , "state"
  // which will be "www.wealthminder.com", "santa-monica" and "CA"
  val CityPage:Regex = """^https?:\/\/(.*)\/financial-advisors-(.*)-([\w]{2})\/?""".r

  val urls = List(
    "https://wealthminder.com/financial-advisors-CA/",
    "https://localhost:8080/financial-advisors-CA/",
    "http://wealthminder.com/financial-advisors-santa-monica-CA",
    "https://localhost:8080/financial-advisors-irvine-CA",
    "https://dev.wealthminder.com/financial-advisors-winston-salem-NC",
    "http://localhost:8080/financial-advisors/CA/san-ramon")

  urls.foreach(url => url match {

    case CityPage(host, city, state) => println(s"City Page found. Host = $host, City = $city, State = $state")

    case _ => System.out.println(s"Invalid URL - $url")
  })





  println("***************** pattern match with Try *****************")



  val list = List("Hulk", "Iron Man", "Black Widow", "Captain America", "Doctor Strange")

  def getSuperHero(index: Int): Try[Option[String]] = Try {
    println(s"fetching random superhero at index = $index")
    if (index == 10 || index == 0) throw new RuntimeException("Just simulating a random exception.")
    val superhero: String = list(index)
    Some(superhero)
  }


  val superHero: Option[String] =
    getSuperHero(Random().nextInt(10)) match {
      case Success(hero) => hero
      case Failure(exception) => exception match {
        case e: ArrayIndexOutOfBoundsException =>
          println("Got an ArrayIndexOutOfBoundException")
          None
        case e: Throwable =>
          println(s"Got a random Runtime Exception: ${e}")
          None
      }
    }

  superHero.foreach(hero => println(s"Superhero = $hero"))

}
