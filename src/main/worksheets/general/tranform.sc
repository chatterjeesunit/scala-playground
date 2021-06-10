case class empInput(
firstName:String,
variable:String,
value:String
 )
case class empOut(
                 firstName:String,
                 salary:String,
                 tax:String,
                 ppf:String
                   )
val data:List[empInput] = List(
  empInput("Sid", "salary", "1000"),
  empInput("Amol", "tax", "20"),
  empInput("Sid", "tax", "10"),
  empInput("Amol", "salary", "2000"),
  empInput("Harshal", "tax", "30"),
  empInput("Harshal", "ppf", "300"),
  empInput("Amol", "ppf", "200"),
  empInput("Sid", "ppf", "100"),
  empInput("Harshal", "salary", "3000"))
def getValue(empVal:List[empInput], fieldName:String) : String =
  empVal.filter(ei => ei.variable.equals(fieldName)).head.value
val result = data
  .groupBy(_.firstName)
  result.map{ obj =>
  empOut
    (obj._1,
     getValue(obj._2, "salary"),
     getValue(obj._2, "tax"),
      getValue(obj._2, "ppf")
    )
  }