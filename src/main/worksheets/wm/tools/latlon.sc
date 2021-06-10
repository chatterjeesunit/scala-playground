import com.wealthminder.common.util.GeoUtil
import org.geo.GeoLocation

val userLat:Double = 37.5331
val userLong:Double = -122.2486
val dist:Double = 50

//Reference - http://www.scribd.com/doc/2569355/Geo-Distance-Search-with-MySQL
val oneDegreeToMiles = 69.172
val minLong = userLong-dist/Math.abs(Math.cos(userLat)*oneDegreeToMiles);
val maxLong = userLong+dist/Math.abs(Math.cos(userLat)*oneDegreeToMiles);
val minLat = userLat-(dist/oneDegreeToMiles);
val maxLat = userLat+(dist/oneDegreeToMiles);

val kmToMile = 0.621371

//Reference http://en.wikipedia.org/wiki/Earth_radius
val equatorialRadius = 6356.7523
//Reference http://en.wikipedia.org/wiki/Earth_radius
val polarRadius = 6356.7523
/*
The distance from the Earth's center to a point on the spheroid surface
at geodetic latitude,i.e., Earth radius at a given latitude, according to the WGS-84 ellipsoid
Reference http://en.wikipedia.org/wiki/Earth_radius
*/
def geocentricRadius(latitude:Double):Double = {

  val An = math.pow(math.pow(equatorialRadius, 2) * math.cos(latitude), 2)
  val Bn = math.pow(math.pow(polarRadius, 2) * math.sin(latitude), 2)
  val Ad = math.pow(equatorialRadius * math.cos(latitude), 2)
  val Bd = math.pow(polarRadius * math.sin(latitude), 2)
  math.sqrt((An + Bn) / (Ad + Bd))
}
val earthMeanRadiusInMiles = 6371.0 * kmToMile
val geoCentricRadiusInMiles = geocentricRadius(userLat) * kmToMile

"http://search-wm-marketplace-test-ntxveicmqpzfgsg3spmawzu5zi.us-east-1.cloudsearch.amazonaws.com/2013-01-01/search?q=cert*&return=_all_fields,distance"+
s"&expr.distance=$kmToMile*haversin($userLat,$userLong,location_geocode.latitude,location_geocode.longitude)" +
s"&fq=location_geocode:['$maxLat,$minLong','$minLat,$maxLong']"+
"&size=25&sort=distance desc"

def getBoundingBox(userLatitude:Double, userLongitude:Double, distanceInMiles:Double, earthRadius:Double):Array[GeoLocation] = {
  val gl = GeoLocation.fromDegrees(userLatitude, userLongitude)
  gl.boundingCoordinates(distanceInMiles, earthRadius)
}

val bounds:Array[GeoLocation] = getBoundingBox(userLat, userLong, dist, geoCentricRadiusInMiles)
val minLatitude = bounds(0).getLatitudeInDegrees
val minLongitude = bounds(0).getLongitudeInDegrees
val maxLatitude = bounds(1).getLatitudeInDegrees
val maxLongitude = bounds(1).getLongitudeInDegrees

"http://search-wm-marketplace-test-ntxveicmqpzfgsg3spmawzu5zi.us-east-1.cloudsearch.amazonaws.com/2013-01-01/search?q=cert*&return=_all_fields,distance,incity"+
  s"&expr.distance=$kmToMile*haversin($userLat,$userLong,location_geocode.latitude,location_geocode.longitude)" +
  s"&expr.incity=(distance>0?false:true)" +
  s"&fq=location_geocode:['$maxLatitude,$minLongitude','$minLatitude,$maxLongitude']"+
  "&size=25&sort=distance desc"


GeoUtil.haversine(37.5331, -122.2486, minLatitude, minLongitude)
GeoUtil.haversine(37.5331, -122.2486, maxLatitude, maxLongitude)

val cityLat:Double = 38.93520200
val cityLon:Double = -77.18672000

GeoUtil.getBoundingBox(cityLat,cityLon, 25)
getBoundingBox(cityLat, cityLon, dist, geoCentricRadiusInMiles)


GeoUtil.getBoundingBox(37.53315580, -122.24837260, 50) // Pincode 94065
GeoUtil.getBoundingBox(26.74901520, -82.26167170, 50) // Pincode 33921

//GeoUtil.haversineDistance((37.5331, -122.2486), (minLatitude, minLongitude))
//GeoUtil.haversineDistance((37.5331, -122.2486), (maxLatitude, maxLongitude))