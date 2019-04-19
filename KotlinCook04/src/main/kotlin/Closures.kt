package chap04

// 코틀린에서 클로저(Closure) 예
// 자바8 의 람다는 외부 변수의 값을 읽을 수는 있지만 쓸 수는 없다
// 코틀린에서는 읽기, 쓰기 모두 가능
fun main() {
    var sum = 0
    var listOfInt = arrayOf(0, 1, 2, 3, 4, 5)
    listOfInt.forEach {
        sum += it
    }
    println("sum = $sum")
}