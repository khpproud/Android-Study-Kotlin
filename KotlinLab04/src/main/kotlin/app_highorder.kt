package thirteen_one

// 고차 함수
fun hoFun(x1: Int, argFun: (Int) -> Int) {
    val result = argFun(10)
    println("x1 : $x1, someFun1 : $result")
}

// 컬렉션 타입의 filter, forEach 함수 이용
val array = intArrayOf(10, 2, 15, 22, 4, 71)

// 함수 타입의 매개변수가 여러개 일때
fun hoFun1(no: Int, argFun1: (Int) -> Int, argFun2: (Int) -> Boolean) {
}

// 매개변수 기본 값 선언
fun some(x1: Int = 10) {
    println(x1)
}


// 함수 타입 기본 값 선언
fun hoFun2(
    x1: Int,
    argFun1: (Int) -> Int,
    argFun2: (Int) -> Boolean = { x: Int -> x > 10}
) {
    val result = argFun1(x1)
    println("result : ${argFun2(result)}")
}

// 함수 반환
fun hoFun3(str: String): (x1: Int, x2: Int) -> Int {
    when (str) {
        "-" -> return {x1, x2 -> x1 - x2}
        "*" -> return {x1, x2 -> x1 * x2}
        "/" -> return {x1, x2 -> x1 / x2}
        else -> return {x1, x2 -> x1 + x2}
    }
}

fun main() {
    hoFun(10) { x -> x * x }

    array.filter { x -> x > 10 }
        .forEach { x -> println(x) }

    hoFun1(10, { it * it}, {it > 10})
    hoFun1(10, {it * it}) {it > 10}
    //hoFun1(10) {it * it} {it > 10} // 에러

    some()

    hoFun2(2, { x: Int -> x * x}, { x: Int -> x > 20})
    hoFun2(4, { x: Int -> x * x})

    val resultFun = hoFun3("*")
    println("result * : ${resultFun(10, 5)}")
}