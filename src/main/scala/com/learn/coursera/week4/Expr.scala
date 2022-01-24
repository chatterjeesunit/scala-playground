package com.coursera.week4

/**
  * Created by sunitc on 18/6/16.
  */
trait Expr {
  def eval:Int = this match {
//    case Paranthesis(e) => e.eval
    case Prod(e1, e2) => e1.eval * e2.eval
    case Sum(e1, e2) => e1.eval + e2.eval
    case Num(n) => n
    case _ => throw new IllegalArgumentException("Evaluation failed")
  }

  def equals(other:Expr):Boolean = this.eval == other.eval

  def simplify:Expr = this match {
    case Sum(x:Prod, y:Prod) =>  x match {
      case Prod(a1: Num, b1: Num) => y match {
        case Prod(a2: Num, b2: Num) =>
          if (a1 == a2) Prod(a1, Sum(b1, b2))
          else if (b1 == b2) Prod(Sum(a1, a2), b1)
          else Sum(x, y)
        case _ => Sum(x, y)
      }
      case _ => Sum(x, y)
    }
    case Num(n) => this
    case Sum(x:Expr, y:Expr) => Sum(x.simplify, y.simplify)
    case Prod(x:Expr, y:Expr) => Prod(x.simplify, y.simplify)
  }

  override def toString:String = this match {
//    case Paranthesis(e) => s"( $e )"
    case Prod(e1, e2) => {
      def func(e:Expr):String = e match {
        case Sum(_,_) => s"( $e )"
        case _ => e.toString
      }

      s"${func(e1)} * ${func(e2)}"
    }
    case Sum(e1, e2) => s"$e1 + $e2"
    case Num(n) => n.toString
    case _ => throw new IllegalArgumentException("toString failed")
  }
}

case class Num(n:Int) extends Expr
case class Sum(e1:Expr, e2:Expr) extends Expr
case class Prod(e1:Expr, e2:Expr) extends Expr
//case class Paranthesis(e:Expr) extends Expr
