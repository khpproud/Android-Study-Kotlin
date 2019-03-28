package seven.three


// 컴패니언 객체 확장
class A {
    // 확장을 위해 비어있더라도 선언은 해놔야 함
    companion object { }
}

fun A.Companion.foo() {
    println("foo Companion A")
}

fun main() {
    // 클래스 인스턴스가 아닌 클래스 형식을 이용해 이 확장을 호출
    A.foo()

    val list = mutableListOf(1, 2, 3)
    list.add(1)
    // plusAssign 연산자 사용으로 쉽고 직관적으로
    list += 2
    println(list)
}