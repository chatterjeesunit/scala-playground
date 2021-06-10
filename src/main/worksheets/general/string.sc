"34111-123" take 5
"94065" take 5
"940" take 5

val param = "corona-del-mar-CA"
val arr = param.splitAt(param.length-2)
val city = arr._1.take(arr._1.length-1)
val state = arr._2



val query: String = "id,first-name,last-name,email-address,picture-url"
val LinkedInProtectedResourceUrl: String = "https://api.linkedin.com/v1/people/~:(%s)"
String.format(LinkedInProtectedResourceUrl, query)