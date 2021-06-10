//Example One: Creating Curried Functions
val sum: (Int,Int)=>Int = _ + _
def add(x: Int, y: Int): Int = x + y

def sumCurried: Int => (Int => Int) = sum.curried
def addCurried(x: Int)(y: Int): Int = x + y
sum(1,5)  // 6
add(1,5)  // 6
sumCurried(1)(5) // 6
addCurried(1)(5)  // 6



//Curried Function: Example Two
import scala.annotation.tailrec
def functionOp(f:Int=>Int)(combine:(Int,Int) => Int)(identity: Int)(start:Int)(end:Int):Int = {
  @tailrec
  def funcLoop(current:Int, accumulator:Int):Int =
    if(current > end) accumulator
    else funcLoop(current+1, combine(f(current), accumulator))

  funcLoop(start, identity)
}

def id = functionOp(x => x)_
//((Int, Int) => Int) => (Int => (Int => (Int => Int)))

def product = id((x,y) => x*y)(1)
//Int => (Int => Int)

def factorial(n:Int):Int = product(1)(n)
//Int => Int

def sumSquares = functionOp(x=>x*x) ((x,y) => x+y) (0)_
//Int => (Int => Int)

def prodSquares = functionOp(x=>x*x) ((x,y) => x*y) (1)_
//Int => (Int => Int)

factorial(5)  // 120
factorial(10) //3628800
sumSquares(1)(5)  //55
prodSquares(1)(5) //14400


//Curried Function: Example Three
def filter(xs: List[Int], p: Int => Boolean): List[Int] =
  if (xs.isEmpty) xs
  else if (p(xs.head)) xs.head :: filter(xs.tail, p)
  else filter(xs.tail, p)

//Curried Function
def modN(n: Int)(x: Int) = ((x % n) == 0)

val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
filter(nums, modN(2)) //List(2, 4, 6, 8)
filter(nums, modN(3)) //List(3, 6)