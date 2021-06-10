
val mnem:Map[Char, String] = Map (
  '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")


val file = scala.io.Source.fromFile("/datadrive/learn/Projects/Coursera/Scala-ProgFun001/src/main/resources/linuxwords.txt")
val words =
  file .getLines().filter(_.forall(_.isLetter)).map(_.toUpperCase).toList

val num = "7225247386"
encode(num).mkString("\n")


val charToNum:Map[Char,Char] = mnem.flatMap{ case(x , y) =>
  y.map ( c =>  c -> x ).toMap
}

def wordToCode(word:String):String = word.toUpperCase map charToNum

val codeGroups:Map[String, List[String]] =
  words groupBy wordToCode withDefaultValue List()

def encode(number:String):Set[List[String]] ={
  if(number.isEmpty) Set(List())
  else {
    for {
      pos <- 1 to number.length
      word <- codeGroups(number take pos)
      rest <- encode(number drop pos)
    } yield (word :: rest)
  }.toSet
}

