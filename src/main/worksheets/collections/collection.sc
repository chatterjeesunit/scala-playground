import com.wealthminder.marketplace.data.dto.permissions.PermissionEnum
import scala.collection.mutable.ListBuffer
import scala.util.Try
val list = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)
val gl = list grouped 4
gl.next
gl.next
gl.next
gl.next
gl.next
gl.hasNext
val sl = list sliding 4
sl.next
sl.next
case class EngineerSkill(name:String, skill:String)
case class PersonSkillCount(name:String, count:Long)
val listEngg = List (
  EngineerSkill("Sunit", "Java"),
  EngineerSkill("Surya", "Java"),
  EngineerSkill("Vishwas", "Java"),
  EngineerSkill("Avanti", "Java"),
  EngineerSkill("Sunit", "Scala"),
  EngineerSkill("Sunit", "Mongo"),
  EngineerSkill("Surya", "Scala"),
  EngineerSkill("Vishwas", "Angular JS"),
  EngineerSkill("Vishwas", "Bootstrap"))
listEngg.groupBy(e => e.name)
listEngg.groupBy(e => e.skill)
val nameCount = listEngg.groupBy(e => e.name).map(kv => PersonSkillCount(kv._1, kv._2.size)).toList.sortBy(p=> (-p.count, p.name))
val testlist = List("this", "maps", "string", "to", "length")
testlist.map(a => a.length -> a)(collection.breakOut).toMap
testlist.groupBy(a => a.length)
list(0)
list(1)
list.take(5)
case class Foo1(a:String)
case class Foo2(x:String)
val m1 = Map (1-> Foo1("a"), 2 -> Foo1("b"), 3 -> Foo1("c"))
val m2 = Map (1-> Foo2("A"),  3 -> Foo2( "C"))
m1.map( f => m2.getOrElse(f._1, Foo2("X")).x).toList
val listBuff:ListBuffer[String] = ListBuffer()
listBuff++=testlist
listBuff.toList
val p=List(1,3,2)
val q=List(4,3,2,3, 1)
p.zip(q).forall(a => a._1 == a._2)
val joinedList = (p++q).distinct

val mval = Map(
    ("abc",
      Map(
        (1, List("a1", "b1", "c1")),
        (2, List("a2", "b2", "c2")),
        (3, List("a3", "b3", "c3"))
      )
    ),
    ("def",
      Map(
        (4, List("d4", "e4", "f4")),
        (5, List("d5", "e5", "f5")),
        (6, List("d6", "e6", "f6"))
      )
    )
)

mval.map(m => (m._1, m._2.values.toList.flatten))
List(None, Some(1), None, None, Some(5)).flatten
val junkMap:Map[String, Int] = Map(("one", 1), ("canUpdateFaq", 2), ("canUpdateEducationHistory", 3), ("Four", 4))
val junkMap2:Map[String, Int] = Map(("canUpdateAffiliations", 1), ("canUpdateEducationHistory", 5), ("canUpdateFaq", 3))

val pf = new PartialFunction[(String,Int), (PermissionEnum.Value, Int)] {
  def apply(origPerm: (String, Int)) = origPerm match {
    case (perm, value) => (PermissionEnum.withName(perm), value)
  }
  def isDefinedAt(origPerm: (String, Int)) = origPerm match {
    case (perm, value) => Try ( PermissionEnum.withName(perm)).isSuccess
    case _ => false
  }
}



junkMap.collect(pf)

PermissionEnum.values.toList


val idsSorted:List[String] = List("Vishwas", "Sunit", "Surya")
val obj = List(EngineerSkill ("Surya", "Backend"), EngineerSkill("Sunit", "Lead"),EngineerSkill("Vishwas", "UI"))
idsSorted.indexOf("Vishwas")
obj.sortWith((e1, e2) => idsSorted.indexOf(e1.name) < idsSorted.indexOf(e2.name))


val results:List[Long] = List(2l, 5l, 1l, 8l, 9l, 7l)
val expected:List[Long] = List(5l, 9l, 7l)
val notExpected:List[Long] = List(3l, 10l, 12l)


expected.filterNot(results.toSet)
notExpected.filter(results.toSet)


