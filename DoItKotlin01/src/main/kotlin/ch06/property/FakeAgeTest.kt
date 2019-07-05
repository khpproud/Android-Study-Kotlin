package ch06.property

// 세터를 이용한 예제
class FakeAge() {
    var age: Int = 0
    set(value) {
        field = when {
            value < 18 -> 18
            value in 18.. 30 -> value
            else -> value - 3
        }
    }
}

fun main() {
    val kim = FakeAge()
    kim.age = 15
    println("Kim's real age = 15, pretended age = ${kim.age}")

    val hong = FakeAge()
    hong.age = 35
    println("Hong's real age = 35, pretended age = ${hong.age}")
}