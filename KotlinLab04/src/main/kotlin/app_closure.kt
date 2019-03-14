package thirteen_three

// 함수 외부 데이터 이용
fun closureTest(x: Int): (Int) -> Int {
    println("argument $x")
    return { it * 10}
}

// 외부 변수 이용
fun closureTest2(x: Int): (Int) -> Int {
    println("argument $x")
    return {
        it * x
    }
}

// 외부 변수 변경
fun closureTest3(): (Int) -> Unit {
    var sum = 0
    return {
        for (i in 1.. it) {
            sum += i
        }
    }
}

fun main() {
    val someFun1 = closureTest(2)
    val someFun2 = closureTest(3)

    println(someFun1(10))
    println(someFun2(10))

    val someFun3 = closureTest2(2)
    val someFun4 = closureTest2(3)

    println(someFun3(10))
    println(someFun4(10))
}