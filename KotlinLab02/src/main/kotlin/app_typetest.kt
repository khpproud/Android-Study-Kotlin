fun getLength(obj: Any): Int {
    return if (obj is String)
        obj.length
    else
        0
}

fun cases(obj: Any): String {
    return when (obj) {
        1 -> "one"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "unknown"
    }
}

fun getStringLength(obj: Any): Int? {
    return if (obj !is String)
        null
    else
        obj.length
}

fun main() {
    println(getLength("Hello"))
    println(getLength(10))
    println(getStringLength("HelloKotlin"))

    println(cases(1))
    println(cases("Hello"))
    println(cases("%$"))
    println(cases(500L))
}