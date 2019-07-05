package ch05_class

// 이너 클래스에서 바깥 클래스 참조하기
open class Base {
    open val x: Int = 1
    open fun f() = println("Base Class f()")
}

class Child : Base() {
    override val x: Int = super.x + 1
    override fun f() = println("Child Class f()")

    // inner 클래스
    inner class Inside {
        fun f() = println("Inside Class f()")
        fun test() {
            f()                     // inner 클래스의 f() 접근
            Child().f()             // 바깥 클래스의 f() 접근
            super@Child.f()         // Child 의 상위클래스인 Base 클래스의 f() 접근
            println("[Inside] super@Child.x: ${super@Child.x}")     // Base 의 x 접근
        }
    }
}

fun main() {
    val c1 = Child()
    c1.Inside().test()              // inner 클래스 Inside 의 메서드 test() 실행
}