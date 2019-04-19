package chap04

// 함수를 인수로 전달하는 예

fun main() {
    // 함수를 람다로 선언
    val funcMultiPly = { a: Int, b: Int -> a* b }
    println(funcMultiPly(4, 3))
    val funcSayHi = { name: String -> println("Hi $name") }
    funcSayHi("Kim")

    // 명시적으로 인수 유형과 반환 유형을 선언
    val funcSum: (Int, Int) -> Int = { a: Int, b: Int -> a + b}
    println(funcSum(5, 2))
    val funcSayHello: (String) -> Unit = { name: String -> println("Hello $name") }
    funcSayHello("Lee")

    // 함수에 인수로 함수를 전달
    performMath(3, 4, funcMultiPly)

    val producePrice1 = 600         // 499 이상은 무료배송
    val producePrice2 = 300         // 배송비 추가함
    val totalCost1 = totalCost(producePrice1)
    val totalCost2 = totalCost(producePrice2)
    println("Total cost for item 1 is ${totalCost1(producePrice1)}")
    println("Total cost for item 2 is ${totalCost2(producePrice2)}")
}

// 람다 함수를 다른 고차 함수의 인수로 전달
fun performMath(a: Int, b: Int, mathFunc: (Int, Int) -> Int): Unit {
    println("value of calculation: ${mathFunc(a, b)}")
}

// 함수를 반환
fun totalCost(producerCost: Int): (Int) -> Int {
    if (producerCost > 499) {
        return { x -> x }
    } else {
        return { x -> x + 50 }
    }
}