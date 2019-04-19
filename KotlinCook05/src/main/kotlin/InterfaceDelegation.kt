package chap05

// 인터페이스 위임
interface A {
    fun foo() {
        println("foo from A")
    }
}

class InterfaceImplementation(var a: A) {
    fun someMethod() {
        a.foo()
    }
}

// foo 메소드에 대한 호출을 A 인터페이스를 구현한 객체에 위임
fun main() {
    var imple = InterfaceImplementation(object : A { })
    imple.someMethod()
}

// 인터페이스의 함수를 자신의 것처럼 직접 사용하도록 할 수 있음
class InterfaceDelegate(val a: A): A by a {
    fun someMethod() {
        foo()
    }
}