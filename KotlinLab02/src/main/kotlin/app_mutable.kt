fun main() {
    // 불변 list
    val immutableList: List<String> = listOf("hello", "world")
    println("${immutableList[0]}.. ${immutableList[1]}")

    // 가변 list
    val mutableList: MutableList<String> = mutableListOf("hello", "world")
    mutableList.add("Park")
    mutableList[1] = "Korea"
    println("${mutableList[0]}.. ${mutableList[1]}.. ${mutableList[2]}")

    // ArrayList 이용
    val arrayList: ArrayList<String> = ArrayList()
    arrayList.add("Hello")
    arrayList.add("World")
    arrayList[1] = "Korea"
    println("${arrayList[0]}.. ${arrayList[1]}")

    // 불변 map
    val immutableMap1 = mapOf(Pair("one", "Hello"), Pair("two", "world"))
    println("${immutableMap1["one"]}.. ${immutableMap1["two"]}")

    val immutableMap2 = mapOf("one" to "hello", "two" to "world")
    println("${immutableMap2["one"]}.. ${immutableMap2["two"]}")

    // 가변 map
    val mutableMap = mutableMapOf<String, String>()
    mutableMap["one"] = "hello"
    mutableMap["two"] = "world"
    println("${mutableMap["one"]}.. ${mutableMap["two"]}")

    // 불변 set
    val immutableSet = setOf("Hello", "Hello", "World")
    println("${immutableSet.elementAt(0)}.. ${immutableSet.elementAt(1)}")

    // 가변 set
    val mutableSet = mutableSetOf<String>()
    mutableSet.add("Hello")
    mutableSet.add("Set")
    println("${mutableSet.elementAt(0)}.. ${mutableSet.elementAt(1)}")
}