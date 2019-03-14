// 반복문
fun loopPrint(no: Int = 1) {
    var count = 1
    while (true) {
        println("loopPrint..")
        if (no == count)
            return
        count++
    }
}

// 재귀
fun recPrint(no: Int = 1, count: Int = 1) {
    println("recPrint..")
    if (no == count) return else recPrint(no - 1, count)
}

// tailrec 사용
tailrec fun tailrecPrint(no: Int = 1, count: Int = 1) {
    println("tailrecPrint...")
    return if (no == count) return else tailrecPrint(no - 1, count)
}

// 꼬리 재귀가 아님
tailrec fun sum(n: Int): Int {
    return if (n <= 0)
        n
    else
        n + sum(n - 1)
}

// 꼬리 재귀
tailrec fun sum2(n: Int, result: Int = 0): Int {
    return if (n <= 0)
        result
    else
        sum2(n - 1, n + result)
}

fun main() {
    loopPrint(3)
    recPrint(3)
    tailrecPrint(3)

    println(sum(3))
    println(sum(3))
}