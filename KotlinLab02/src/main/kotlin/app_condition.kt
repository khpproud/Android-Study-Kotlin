fun main() {
    val a = 5
    val result = if (a > 10) "hello" else "world"
    println(result)

    val result2 = if (a < 10) {
        print("hello....")
        10 + 20
    } else {
        print("world....")
        20 + 20
    }
    println(result2)

    val a2 = 3
    when (a2) {
        1 -> println("a2 == $a2")
        2 -> println("a2 == $a2")
        else -> println("a2 is neither 1 or 2")
    }

    val data = 40
    when (data) {
        10, 20 -> println("data is 10 or 20")
        30, 40 -> println("data is 30 or 40")
        some() -> println("data is 50")
        30 + 30 -> println("data is 60")
    }

    val data2 = 15
    when (data2) {
        !in 1.. 100 -> println("invalid data")
        in 1.. 10 -> println("1 <= data2 <= 10")
        in 11.. 20 -> println("11 <= data2 <= 20")
        else -> println("data2 > 20")
    }

    val list = listOf<String>("hello", "world", "park")
    val array = arrayOf("one", "two", "three")
    val data3 = "park"
    when (data3) {
        in list -> println("data3 is list")
        in array -> println("data3 in array")
        else -> println("data3 not exist")
    }

    testWhen(false)

    val data4 = 15
    when {
        data4 <= 10 -> println("data4 <= 10")
        data4 in 11..20 -> println("10 < data4 <= 20")
        else -> println("data4 > 20")
    }

    val data5 = 3
    val result3 = when (data5) {
        1 -> "1..."
        2 -> "2..."
        else -> {
            println("else...")
            "hello"
        }
    }

    println(result3)
}

fun some() = 50

fun testWhen(data: Any) {
    when (data) {
        1 -> println("data value is 1")
        "hello" -> println("data value is hello")
        is Boolean -> println("Data type is Boolean")
    }
}