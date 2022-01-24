import com.coursera.week3.binarytree._

val tree1:IntSet = Empty.include(5).include(3).include(9)


val tree2:IntSet = new BinaryTreeSet(2, Empty, Empty) include 1 include 6 include 4

val tree3:IntSet = tree2 union tree1

def p(x:Int):Boolean = x%2 != 0


tree1.filter(p)
tree2.filter(p)

//Empty.highest
tree3.highest