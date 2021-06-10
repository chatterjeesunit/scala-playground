import com.wealthminder.common.util.{GeoLocationBoundingBox, GeoUtil}

val oneDegreeLatitudeToMiles:Double = 69.172

//Reference - http://www.scribd.com/doc/2569355/Geo-Distance-Search-with-MySQL
def getBoundingBox(userLatitude:Double, userLongitude:Double, distanceInMiles:Double):GeoLocationBoundingBox = {

  val minLatitude = userLatitude - (distanceInMiles/oneDegreeLatitudeToMiles);
  val maxLatitude = userLatitude + (distanceInMiles/oneDegreeLatitudeToMiles);

  val minLongitude = userLongitude - distanceInMiles/Math.abs(math.cos(userLatitude.toRadians)*oneDegreeLatitudeToMiles);
  val maxLongitude = userLongitude + distanceInMiles/Math.abs(math.cos(userLatitude.toRadians)*oneDegreeLatitudeToMiles);

  GeoLocationBoundingBox(minLatitude, minLongitude, maxLatitude, maxLongitude)
}

//Lat Lon of Arlington VA - 22101
val cityLat:Double = 38.93520200
val cityLon:Double = -77.18672000
val radius:Int = 25

val geoBound:GeoLocationBoundingBox = getBoundingBox(cityLat, cityLon, radius)

val minLatitude = geoBound.minLatitude
val maxLatitude = geoBound.maxLatitude

val minLongitude = geoBound.minLongitude
val maxLongitude = geoBound.maxLongitude

val dist1 =
  GeoUtil.haversine(cityLat, cityLon, minLatitude, minLongitude)

val dist2 =
  GeoUtil.haversine(cityLat, cityLon, maxLatitude, maxLongitude)
