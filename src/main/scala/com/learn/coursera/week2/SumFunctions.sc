def fact(n:Int):Int = if(n==1) 1 else  n * fact(n-1)
def square(n:Int):Int = n*n

def sumInts(a:Int, b:Int):Int = if(a > b) 0 else a + sumInts(a+1, b)
def sumFact(a:Int, b:Int):Int = if(a > b) 0 else fact(a) + sumFact(a+1, b)
def sumSquare(a:Int, b:Int):Int = if(a > b) 0 else square(a) + sumSquare(a+1, b)


sumInts(2, 5)
sumFact(2, 5)
sumSquare(2, 5)



