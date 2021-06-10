import scala.annotation.tailrec

val list = List(1,2,3,4,5,6)

val result = list.map( x => 3)

var last:Int = 10

var found = false;
(0 to last) foreach (i => {
  found = if(!found && i > 0 && i%5==0) {
    last = i
    println(s"***$i")
    true
  } else found

  println(i)
})

