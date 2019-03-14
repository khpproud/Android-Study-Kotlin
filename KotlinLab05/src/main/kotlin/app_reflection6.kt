package eighteen_one_six

// 고차 함수에서의 함수 레퍼런스 이용
fun isOdd(x: Int): Boolean = x % 2 != 0

fun reflectionFun(argFun: (Int) -> Boolean) {
    println("Result : ${argFun(3)}")
}

// filter() 고차 함수 이용
fun myFun(no: Int): Boolean {
    return no > 10
}

fun main() {
    reflectionFun { n -> isOdd(n) }
    reflectionFun(::isOdd)

    val array = arrayOf(10, 5, 25, 7, 30)

    println("Lambda...")
    array.filter { it > 10 }
        .forEach { println(it) }
    println("Method Reference...")
    array.filter(::myFun)
        .forEach { println(it) }
}