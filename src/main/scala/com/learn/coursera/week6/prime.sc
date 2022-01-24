def isPrime(x:Integer):Boolean = 2 to x-1 forall(x%_ != 0)

1 to 100 filter(isPrime(_))

def prime1(n:Integer):List[(Int,Int)] = {
  val res =
    2 to n-1 map ( x =>
      1 to x-1 map ( y =>
        (x,y)
        )
    )
  res.flatten.toList.filter{case (x,y) => isPrime(x+y)}
}


def prime2(n:Integer):List[(Int,Int)] = {
  val res =
    2 to n-1 flatMap ( x =>
      1 to x-1 map ( y =>
        (x,y)
        )
      )
  res.toList.filter{case (x,y) => isPrime(x+y)}
}

def prime3(n:Integer):List[(Int,Int)] = {

  val res = for {
    i <- 2 to n
    j <- 1 to i if isPrime(i+j)
  }yield(i,j)

  res.toList
}

prime1(15)
prime2(15)
prime3(15)