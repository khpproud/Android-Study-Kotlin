package seven.two

abstract class A
class B: A()

fun A.foo() {
    println("foo A")
}
fun B.foo() {
    println("foo B")
}

fun main() {
    // 확장은 정적으로 확인됨
    // 컴파일 시에 여기에 어떤 객체가 있는지 알 수 없기 때문에 A의 확장 함수가 사용
    val b = B()
    b.foo()
    (b as A).foo()
    val a: A = b
    a.foo()
}

