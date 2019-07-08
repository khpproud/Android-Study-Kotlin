package ch07_variable_class

// 추상 클래스의 객체 인스턴스
abstract class Printer {
    abstract fun print()
}

val myPrinter = object : Printer() {
    override fun print() {
        println("출력!!!")
    }
}

fun main() {
    myPrinter.print()
}