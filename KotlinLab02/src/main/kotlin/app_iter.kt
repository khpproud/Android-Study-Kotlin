fun main() {
    var sum: Int = 0
    for (i in 1.. 10) {
        sum += i
    }
    println(sum)

    val list = listOf("Hello", "World", "!")
    val sb = StringBuilder()
    for (str in list) {
        sb.append("$str ")
    }
    println(sb.toString())

    for (i in list.indices) {
        println(list[i])
    }

    for (i in 0 until list.size) {
        println(list[i])
    }

    for ((index, value) in list.withIndex()) {
        println("the element at $index is $value")
    }

    for (i in 1 until 11 step 2) {
        println(i)
    }

    var x = 0
    var sum1 = 0
    while (x < 10)
        sum1 += ++x
    println(sum1)

    var x2 = 0
    var sum2 = 0
    while (true) {
        sum2 += ++x2
        if (x2 == 10)
            break
    }

    println(sum2)

    for (i in 1.. 3) {
        for (j in 1.. 3) {
            if (j > 1) continue
            println("i : $i, j : $j")
        }
    }

    // 라벨이용
    aaa@ for (i in 1.. 3) {
        for (j in 1.. 3) {
            if (j > 1) break@aaa
            println("i : $i, j : $j")
        }
    }
}