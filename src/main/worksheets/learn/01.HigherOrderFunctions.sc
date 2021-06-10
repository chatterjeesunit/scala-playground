import scala.annotation.tailrec

def func(f: Int => Int, op:(Int,Int)=> Int, start:Int, end:Int, identity:Int):Int = {

  @tailrec
  def funcloop(current:Int, accumulator: Int):Int = {
    if(current > end) accumulator
    else funcloop(current+1, op(f(current) , accumulator))
  }

  funcloop(start, identity)
}

//Sum of cubes
func(x=> x*x*x, (x,y) => x+y, 1, 5, 0)

//sum from a to b
func(x=>x, (x,y) => x+y , 1, 10, 0)


//Product of cubes
func(x=> x*x*x, (x,y)=> x*y, 1, 5, 1)


//factorial of a 10
func(x=>x, (x,y) => x*y, 1, 10, 1)