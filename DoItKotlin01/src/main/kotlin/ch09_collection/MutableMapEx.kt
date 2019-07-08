package ch09_collection

// 가변형 Map 사용 예
fun main() {
    val capitalCityMap: MutableMap<String, String> =
        mutableMapOf("Korea" to "Seoul", "China" to "Beijing", "Japan" to "Tokyo")
    println(capitalCityMap.keys)
    println(capitalCityMap.values)

    capitalCityMap["UK"] = "London"
    capitalCityMap.remove("China")
    println(capitalCityMap)

    val addData = mutableMapOf("USA" to "Washington")
    capitalCityMap.putAll(addData)
    println(capitalCityMap)

    //HashMap
    val hashMap = hashMapOf(1 to "Hello", 2 to "World")
    println(hashMap)

    // SortedMap
    val sortedMap = sortedMapOf(1 to "Apple", 2 to "Banana")
    println(sortedMap)

    // LinkedHashMap
    val linkedHashMap = linkedMapOf(1 to "Computer", 2 to "Mouse")
    println("linkedHashMap = $linkedHashMap")
}