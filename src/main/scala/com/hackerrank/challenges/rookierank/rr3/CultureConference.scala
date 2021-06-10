package com.hackerrank.challenges.rookierank.rr3

import java.util.Scanner
import scala.collection.immutable.ListMap
import scala.collection.mutable

/**
  * Created by sunitc on 5/7/17.
  * https://www.hackerrank.com/contests/rookierank-3/challenges/culture-conference
  */
/*
Test Case 1
Input
5
0 0
1 0
2 0
2 0
Expected output
1 (employee 2)

Test Case 2
Input
8
0 1
1 0
2 1
1 1
0 1
2 0
5 0
Expected output
2 (2,7 or 6,7)
 */
object CultureConference {
  case class Emp(empId: Int, empSupId: Int, burnt: Boolean) {
    override def toString: String = s"[$empId -> $empSupId : $burnt]"
  }

  def main(args: Array[String]) {
    var (burnedEmpList: List[Int], nearbyBurnedEmp: ListMap[Int, List[Int]]) = getInitialData
    //    println(nearbyBurnedEmp)
    var empVacationList:scala.collection.mutable.MutableList[Int] = new mutable.MutableList[Int]()

    while(!burnedEmpList.isEmpty) {
//      Thread.sleep(100)
//      println("\t.......................................................")
//      println(s"\tBurned Employees = $burnedEmpList")
//      println(s"\tEmployees on Vacation = $empVacationList")
//      println(s"\tNearby Employee Map = $nearbyBurnedEmp")
      val (empToRemove:Int, nearbyEmployees: List[Int]) = nearbyBurnedEmp.head
      if(!burnedEmpList.filter(be => nearbyEmployees.contains(be)).isEmpty) {
        burnedEmpList = burnedEmpList.filterNot(e => nearbyEmployees.contains(e))
        nearbyBurnedEmp = nearbyBurnedEmp.tail
        empVacationList.+=(empToRemove)
      }else {
        nearbyBurnedEmp = nearbyBurnedEmp.tail
      }
    }

//    val listOfEmployees: List[Int] =
//      calculateEmpVacation(burnedEmpList, burntEmpNearbyBurntEmpList, List())
//
    println(empVacationList.size)
  }

  private def getInitialData = {
    val sc = new java.util.Scanner(System.in)
    var n = sc.nextInt()
    // Information for employees 1 through n - 1:
    // The first value is the employee's supervisor ID
    // The second value is the employee's burnout status (0 is burned out, 1 is not)

    val empList: List[Emp] = readEmployees(sc, n)
    //    println(empList)

    val burnedEmpMap: Map[Int, Boolean] = empList.withFilter(_.burnt).map(e => (e.empId -> e.burnt)).toMap
    //    println(burnedEmpMap)

    val burntChildList: Map[Int, List[Int]] =
      empList
        .groupBy(_.empSupId)
        .mapValues(el => el.withFilter(_.burnt).map(_.empId))
        .filterNot(_._2.isEmpty)

    //    println(burntChildList)

    //Nearby burned employees for a given burned employee (sorted in decreasing order)
    val burntEmpNearbyBurntEmpList =
      ListMap(empList
        .map(be => {
          val burntSuperior: Option[Boolean] = burnedEmpMap.get(be.empSupId)
          val burnedChildren: List[Int] = burntChildList.getOrElse(be.empId, List())
          val result: List[Int] = burntSuperior match {
            case Some(true) => be.empSupId :: burnedChildren
            case _ => burnedChildren
          }
          (be.empId -> (if (be.burnt) be.empId :: result else result))
        }
        ).toMap.toSeq.sortBy(kv => (0-kv._2.size) * 100 + kv._1): _*)
    (burnedEmpMap.keys.toList, burntEmpNearbyBurntEmpList)
  }

//  private def calculateEmpVacation(burnedEmpList:List[Int],
//                                   nearbyBurnedEmp: ListMap[Int, List[Int]],
//                                   empVacationList:List[Int]): List[Int] = {
////    println("\t.......................................................")
////    println(s"\tBurned Employees = $burnedEmpList")
////    println(s"\tEmployees on Vacation = $empVacationList")
////    println(s"\tNearby Employee Map = $nearbyBurnedEmp")
//    if(burnedEmpList.isEmpty) {
//      empVacationList
//    }
//    else {
//      val (empToRemove:Int, nearbyEmployees: List[Int]) = nearbyBurnedEmp.head
//      val remainingBurnedEmp: List[Int] = burnedEmpList.filterNot(e => nearbyEmployees.contains(e))
//
//      calculateEmpVacation(
//        remainingBurnedEmp,
//        nearbyBurnedEmp.filter(kv =>
//          !nearbyEmployees.contains(kv._1)
//            && !remainingBurnedEmp.filter(be => kv._2.contains(be)).isEmpty),
//        empToRemove :: empVacationList
//      )
//    }
//  }

  private def readEmployees(sc: Scanner, n: Int): List[Emp] = {
    val empMutableList: mutable.MutableList[Emp] = new mutable.MutableList[Emp]()
    for (i <- 1 to n-1) {
      empMutableList.+=(Emp(i, sc.nextInt(), sc.nextInt() match { case 0 => true case _ => false }))
    }
    //Add CEO
    empMutableList.+=(Emp(0,0,false))
    empMutableList.toList
  }
}
