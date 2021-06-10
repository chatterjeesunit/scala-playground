import org.json4s.JsonAST.{JField, JObject}
import org.json4s.JsonDSL._

case class Latlon(latitude:Double, longitude:Double)

val locationGeoCodes:List[Latlon] =
  List(Latlon(17.5, 94.56), Latlon(98.78, -122.34))

val results =
  locationGeoCodes
    .zipWithIndex
    .map{ll =>
      val id = s"location_geocode_${ll._2+1}"
      val value = s"${ll._1.latitude.toString},${ll._1.longitude.toString}"
    JField(id , value)
    }


JObject(results)
//results.foldLeft(JField("","")){(a,z) => (a) ~ (z)}

val res:JObject = ("name" -> "Sunit") ~ ("age" -> 35)