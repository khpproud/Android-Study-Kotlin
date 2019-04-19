package chap05

// 코틀린에서의 다형성
// 컴파일 타임의 다형성: 매개변수의 타입에 따라 호출할 함수를 결정
fun doubleOf(a: Int): Int {
    println(a)
    return 2 * a
}

fun doubleOf(a: Float): Float {
    println(a)
    return 2 * a
}

fun doubleOf(a: Double): Double {
    println(a)
    return 2.00 * a
}

fun main() {
    println(doubleOf(4))
    println(doubleOf(4.3))
    println(doubleOf(4.323))

    var a = Sup()
    a.method1()
    a.method2()
    var b = Sum()
    b.method1()
    b.method2()
}

// 런타임의 다형성: 오버라이드 및 오버로드된 메소드에 대한 호출을 처리
open class Sup {
    open fun method1() {
        println("Printing method 1 from inside Sup")
    }
    fun method2() {
        println("Printing method 2 from inside Sup")
    }
}

class Sum: Sup() {
    override fun method1() {
        println("Printing method 1 from inside Sum")
    }
}
