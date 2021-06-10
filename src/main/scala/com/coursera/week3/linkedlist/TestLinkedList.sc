import com.coursera.week3.linkedlist.{Cons, LinkedList, Nil}

val l1:LinkedList[Int] = new Cons(5, new Nil)
  .addNode(1)
  .addNode(4)
  .addNode(7)
  .addNode(3)
  .addNode(16)

def find(n:Int, list:LinkedList[Int]):Int =
  if (list.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0 ) list.head
  else find(n-1, list.tail)

find(0, l1)
find (2, l1)
find (8, l1)
