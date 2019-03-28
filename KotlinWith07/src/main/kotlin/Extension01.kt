package seven.one

class A {
    fun foo() {
        println("foo from A")
    }
}

fun A.foo() {
    println("foo from Extension")
}

fun main() {
    // 이름과 매개변수가 같은 멤버 함수와 확장 함수가 있으면 항상 멤버 함수가 이김
    A().foo()

    // 상위 클애스의 메서도도 확장 함수를 이김
    C().foo()
    D().foo()
}

open class C {
    fun foo() {
        println("foo from C")
    }
}
class D: C()

fun D.foo() {
    println("foo from Extension")
}