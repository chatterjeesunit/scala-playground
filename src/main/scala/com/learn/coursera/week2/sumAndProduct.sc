
def accumulator(f:Int=>Int, combine:(Int, Int)=> Int, unit:Int)(a:Int, b:Int):Int = if(a > b) unit else combine(f(a), accumulator(f, combine, unit)(a+1, b))


accumulator(x=>x*x, (x,y)=> x+y, 0)(2, 5)
accumulator(x=>x, (x,y)=> x*y, 1)(2, 5)


def factorial(n:Int) = accumulator(x=>x, (x,y)=>x*y, 1)(1, n)


factorial(2)
factorial(3)
factorial(4)
factorial(5)
factorial(6)
factorial(10)


