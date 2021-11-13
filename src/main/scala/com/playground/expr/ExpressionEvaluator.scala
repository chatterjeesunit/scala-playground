package com.playground.expr

import scala.collection.JavaConverters._
import scala.util.matching.Regex

/**
  * Created by sunitc on 1/8/17.
  */
object ExpressionEvaluator {

  val WORD = """([\w\d.@-_\s]*)"""
  val SPACE = """[\s]*"""
  val OPENBRACE = """[\s]*\("""
  val CLOSEBRACE = """[\s]*\)"""
  val COMMA = """[\s]*,*"""
  val ANYFUNC = "(substring|length|upper|lower|concat)"
  val NOTBRACE = "[^()]"
  val ANYCHAR = "(.*)"

  val ANYSTATEMENT = s"$OPENBRACE($ANYCHAR)$CLOSEBRACE"

  val substringRegex: Regex = s"""^substring$OPENBRACE$WORD$COMMA$WORD$COMMA$WORD$CLOSEBRACE$$""".r
  val lengthRegex:Regex = s"""^length$OPENBRACE$WORD$CLOSEBRACE$$""".r
  val upperRegex: Regex = s"""^upper$OPENBRACE$WORD$CLOSEBRACE$$""".r
  val lowerRegex: Regex = s"""^lower$OPENBRACE$WORD$CLOSEBRACE$$""".r
  val concatRegex: Regex = s"""^concat$OPENBRACE$WORD$COMMA$WORD$CLOSEBRACE$$""".r
  val exprRegex: Regex = s"""^$ANYCHAR$ANYFUNC($OPENBRACE$NOTBRACE*$CLOSEBRACE)$ANYCHAR$$""".r
  val stringSurroundByBrace: Regex = s"""^$ANYCHAR$OPENBRACE$WORD$CLOSEBRACE$ANYCHAR$$""".r

  val statementWithOperator: Regex = s"""^$ANYCHAR$OPENBRACE${WORD}(eq|ne|gt|lt|gte|lte)${WORD}${CLOSEBRACE}${ANYCHAR}$$""".r
  val booleanSurroundedByBrace: Regex = s"""^${ANYCHAR}${OPENBRACE}${SPACE}(false|true)${SPACE}${CLOSEBRACE}${ANYCHAR}$$""".r
  val joinOperatorRegex: Regex = s"""^${ANYCHAR}${OPENBRACE}${SPACE}(false|true)${SPACE}(AND|OR)${SPACE}(false|true)${SPACE}${CLOSEBRACE}${ANYCHAR}$$""".r
  val ifStatementRegex: Regex = s"""^if${OPENBRACE}(false|true)${CLOSEBRACE}${SPACE}then(.*)else(.*)$$""".r


  def evalExpression(expr: String, fields: java.util.Map[String, String]) : String = evalExpr(expr)(true, fields.asScala.toMap)

  def evalFormula(formula: String, fields: java.util.Map[String, String]) : String = {
    val fieldMap: Map[String, String] = fields.asScala.toMap
    val simplifiedFormula: String = evalExpr(formula)(false, fieldMap )
    println(simplifiedFormula)
    evalForm(simplifiedFormula)
  }


  private def evalExpr(expr: String)(implicit removeBraces: Boolean, fields: Map[String, String]): String = {
    expr.trim match {
      case substringRegex(field, from, to) => evalExpr(field).substring(evalExpr(from).trim.toInt, evalExpr(to).trim.toInt)
      case upperRegex(field) => evalExpr(field).toUpperCase
      case lowerRegex(field) => evalExpr(field).toLowerCase
      case lengthRegex(field) => evalExpr(field).length.toString
      case concatRegex(str1, str2) => evalExpr(str1).concat(evalExpr(str2))
      case exprRegex(prefix, function, args , suffix) => evalExpr(prefix + evalExpr(function+args) +  suffix)
      case stringSurroundByBrace(prefix, field, suffix) if removeBraces => evalExpr(prefix + evalExpr(field.trim) + suffix)
      case _ => fields.getOrElse(expr.trim, expr.trim)
    }
  }

  private def evalForm(formula: String): String = {
    println(formula.trim)
    formula.trim match {
      case ifStatementRegex(statement, trueResult, falseResult) => if(statement.toBoolean) evalExpr(trueResult)(true, Map()) else evalExpr(falseResult)(true, Map())
      case statementWithOperator(prefix, left, operator, right, suffix) => evalForm(s"${prefix}(${evalOperator(left, operator, right)})${suffix}")
      case booleanSurroundedByBrace(prefix, booleanVal, suffix) => evalForm(s"${prefix}${booleanVal}${suffix}")
      case joinOperatorRegex(prefix, left, joinOperator, right, suffix) => evalForm(s"${prefix}(${evalBooleanJoinOperator(left, joinOperator, right).toString})${suffix}")
      case _ => formula.trim
    }

  }

  private def evalOperator(leftOperand: String, operator: String, rightOperand: String): Boolean = {
    operator.trim() match {
      case "eq" => leftOperand.trim.equals(rightOperand.trim)
      case "ne" => !leftOperand.trim.equals(rightOperand.trim)
      case "lt" => leftOperand.trim.compareTo(rightOperand.trim) < 0
      case "gt" => leftOperand.trim.compareTo(rightOperand.trim) > 0
      case "lte" => leftOperand.trim.compareTo(rightOperand.trim) <= 0
      case "gte" => leftOperand.trim.compareTo(rightOperand.trim) >= 0
    }
  }

  private def evalBooleanJoinOperator(leftOperand: String, joinOperator: String, rightOperand: String): Boolean = {
    joinOperator.trim.toUpperCase match {
      case "AND" => leftOperand.toLowerCase.toBoolean && rightOperand.toLowerCase.toBoolean
      case _ => leftOperand.toLowerCase.toBoolean || rightOperand.toLowerCase.toBoolean
    }
  }
}
