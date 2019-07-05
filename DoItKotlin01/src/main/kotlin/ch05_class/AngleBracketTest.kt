package ch05_class

// 앵글 브래킷을 사용한 이름 중복 해결
open class A {
    open fun f() = println("A Class f()")
    fun a() = println("A Class a()")
}

interface B {
    fun f() = println("B Interface f()")
    fun b() = println("B Interface b()")
}

class C : A(), B {
    override fun f() = println("C Class f()")           // 컴파일 되려면 오버라이딩 되어야 함

    fun test() {
        f()                                             // 현재 클래스의 f
        b()                                             // B 인터페이스의 b
        super<A>.f()                                    // A 클래스의 f()
        super<B>.f()                                    // B 인터페이스의 f()
    }
}

fun main() {
    val c = C()
    c.test()
}