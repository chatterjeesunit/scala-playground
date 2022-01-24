
val mnem:Map[Char, String] = Map (
  '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")


val file = scala.io.Source.fromFile("/datadrive/learn/Projects/Coursera/Scala-ProgFun001/src/main/resources/linuxwords.txt")
val words = file.getLines().map(_.toUpperCase).toSet

val num = "7225247386"

decode(num).mkString("\n")

def getWord(codeList:List[String], current:List[String], acc:Set[List[String]]):Set[List[String]] = {
  if(codeList.isEmpty) acc
  else {
    val wl = for {
      c <- codeList.head
      tmpStr <- current
    } yield {
      tmpStr + c
    }
    val (matchList:List[String], unMatchList:List[String])=
      wl.toList.partition(w => words.contains(w))
    val l1 = getWord(codeList.tail, unMatchList, acc)
    val newAcc = for {
      wordList <- acc
      newWord <- matchList
    }yield {
      wordList ++ List(newWord)
    }
    val l2 = getWord(codeList.tail, List(""), newAcc)
    (l1 ++ l2)
  }
}

def decode(number:String):Set[List[String]] = {
  val codeList = num.map(mnem.get(_)).flatten
  getWord(codeList.toList, List(""), Set(List[String]()))
    .filter(s => (s.foldLeft("")(_+_)).length == number.length)
}