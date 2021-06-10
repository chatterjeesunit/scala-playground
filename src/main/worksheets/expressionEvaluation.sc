

import com.expr.ExpressionEvaluator

import scala.collection.JavaConversions._
val fields = Map (
  ("firstName", "John"),
  ("middleName", "Howard"),
  ("lastName", "Doe"),
  ("id1", "emplo000000000001000"),
  ("id2", "persn000000000001000"),
  ("person_no" , "21001")
)

val expr1: String =
  "concat ( lower( concat(concat( substring(firstName,0,1), substring(middleName,0,1)), lastName)), (@thoughtworks.com))"

ExpressionEvaluator.evalExpression(expr1, fields)

val formula1: String = "if( ((upper(sunit) eq substring(CHATTERJEE,0,4 )) AND (substring(id1, 0,5) eq substring(id2, 0,5))) AND (person_no gt 10000)) then ( length (SUNIT) ) else ( length ( CHATTERJEE) )"
ExpressionEvaluator.evalFormula(formula1, fields)