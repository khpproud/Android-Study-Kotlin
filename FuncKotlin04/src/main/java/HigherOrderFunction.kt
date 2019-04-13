package chap04

// 고차 함수의 예
// 인자로 람다를 받음
fun performOperationOnEven(number: Int, operation: (Int) -> Int): Int {
    return if (number % 2 == 0)
        operation(number)
    else
        number
}

//반환 값이 람다
fun getAnotherFunction(n: Int): (String) -> Unit {
    return {
        println("n : $n, it : $it")
    }
}

fun main() {
    println("4 -> (it * 2)로 호출 : ${performOperationOnEven(4) { it * 2}}")
    println("5 -> (it * 2)로 호출 : ${performOperationOnEven(5) { it * 2}}")

    getAnotherFunction(0)("abc")
    getAnotherFunction(2)("bgf")
    getAnotherFunction(5)("def")
}