val postalCodeMap = Map(
  (1, List(1,2,3,4,5)),
  (5, List(1,2,5, 6, 7)),
  (8, List(7,8,9,10,11,1,12))
)

//postalCodeMap.map{ case (key, values) =>
//  values.map(v => (v -> key))
//}.flatten.groupBy{ case (x,y) => x }.mapValues( _.map{ case (x,y) => y})


//Now we need to find all permutations of local postal codes.
//E.g we got local postal codes of 1, 5, 8
//But we need to calculate the local postal codes of postal codes in values also
//So we will convert the map
// Map(
//  1 -> List(1,2,3,4,5),
//  5 ->  List(1,2,5, 6, 7),
//  8 -> List(7,8,9,10,11,12))
//
// to a map like this
// Map(
// 5 -> List(1, 5),
// 10 -> List(8),
// 1 -> List(1, 5),
// 6 -> List(5),
// 9 -> List(8),
// 2 -> List(1, 5)  ... and so on

postalCodeMap.map{ case (key, values) =>
  values.map(v => (v -> key))
}.flatten.groupBy{ case (x,y) => x }.mapValues( _.map{ case (x,y) => y}.toList)