import com.coursera.week4._

val expr1:Expr = Prod(Sum(Num(1), Num(5)), Num(6))
expr1.toString + " = " + expr1.eval

val expr2:Expr = Prod( Sum(Num(5), Num(2)),Sum(Num(5), Num(8)))
expr2.toString + " = " + expr2.eval

val expr3:Expr = Sum( Prod(Num(5), Num(2)),Prod(Num(5), Num(8)))
expr3.toString + " = " + expr3.simplify.toString + " = " + expr3.eval

val e:Expr = Prod(Sum(Num(5) , Num(8)), Sum(Num(4), Num(3)))
e + " = " + e.eval

val x:Expr = Sum(Prod(Num(5) , Num(8)), Prod(Num(4), Num(3)))
x + " = " + x.eval
x.simplify + " = " + x.simplify.eval

val y :Expr = Sum(Prod(Sum(Num(4),Num(5)), Sum(Num(3), Num(5))), Sum(Num(9), Prod (Num(7), Num(2))))
y + " = " + y.eval
y.simplify + " = " + y.simplify.eval



val z :Expr = Sum(Sum(Prod(Num(4),Num(5)), Prod(Num(3), Num(5))), Sum(Prod(Num(3),Num(7)), Prod(Num(3), Num(5))))
z + " = " + z.eval
z.simplify + " = " + z.simplify.eval