package chap04

// 부수 효과를 일으키는 함수 예
class Calc {
    var a = 0
    var b = 0
    fun addNumber(a: Int = this.a, b: Int = this.b): Int {
        this.a = a
        this.b = b
        return a + b
    }
}

fun main() {
    val calc = Calc()
    println("result calc.addNumber(10, 15) : ${calc.addNumber(10, 15)}")
}