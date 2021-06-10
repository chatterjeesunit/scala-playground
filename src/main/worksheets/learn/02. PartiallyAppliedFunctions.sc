//Example One
def add(a: Int, b: Int) = a + b
val onePlusFive = add(1,5) // 6
val addFour = add(4, _:Int) // addFour: Int => Int = <function1>
val twoPlusFour = addFour(2) // 6

//Example Two : Creating a size filter on Strings
type Predicate = (Int, Int) => Boolean
def sizeConstraint(p: Predicate, n: Int, text: String) = p(text.size, n)

val gte: Predicate = _ >= _ //gte: Predicate = <function2>
val lte: Predicate = _ <= _ //lte: Predicate = <function2>

val minSize: (Int, String)=>Boolean = sizeConstraint(gte, _, _)
//minSize: (Int, String) => Boolean = <function2>

val maxSize: (Int, String)=>Boolean = sizeConstraint(lte, _, _)
//maxSize: (Int, String) => Boolean = <function2>

minSize(5, "Sunit")
minSize(10, "Sunit")

//Example Three: Creating a generic function for sum/product on function applied to range of numbers
//E.g sum of cubes, product of squares, factorial, etc
import scala.annotation.tailrec
def operate(f:Int=>Int, combine:(Int,Int) => Int, identity: Int, start:Int, end:Int):Int = {
  @tailrec
  def funcLoop(current:Int, accumulator:Int):Int =
    if(current > end) accumulator
    else funcLoop(current+1, combine(f(current), accumulator))
  funcLoop(start, identity)
}

def sumF = operate(_:Int=>Int, (x:Int,y:Int) => x+y, 0, _:Int, _:Int)
//(Int => Int, Int, Int) => Int
def prodF = operate(_:Int=>Int, (x:Int,y:Int) => x*y, 1, _:Int, _:Int)
//(Int => Int, Int, Int) => Int
def sumCubes = sumF(x=>x*x*x, _:Int, _:Int )
//(Int, Int) => Int
def prodSquares = prodF(x => x*x, _:Int, _:Int)
//(Int, Int) => Int
def factorial = prodF(x=>x, 1, _:Int)
//Int => Int


sumCubes(1, 5)  // 225
prodSquares(2,4)  // 576
factorial(5)  //120

