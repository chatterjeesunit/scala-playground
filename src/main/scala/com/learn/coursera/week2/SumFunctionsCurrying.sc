def sum(f:Int => Int)(a:Int, b:Int):Int = if (a > b) 0 else f(a) + sum(f)(a+1,b)

val res1 = sum(x => x)(_,_)
val result1 = res1(2,5)

val res2 = sum(x => x*x)(_,_)
val result2 = res2(2,5)


val res3 = sum(x=> x*x*x)(_,_)
res3(2,5)


val result11 = sum(x => x)(2,5)
val result22 = sum(x => x*x)(2,5)
val result33 = sum(x=> x*x*x)(2,5)

def product(f:Int=>Int)(a:Int, b:Int):Int = if(a > b) 1 else f(a) * product(f)(a+1, b)

val prod1 = product(x=>x)(2,3)
val prod2 = product(x=>x*x)(2,3)
val prod3 = product(x=>x*x*x)(2,3)

