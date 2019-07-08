package ch08_generic

// 다수 조건의 형식 매개변수 제한하기
interface InterA
interface InterB

class HandlerA : InterA, InterB
class HandlerB : InterA

class ClassA<T> where T: InterA, T: InterB      // 2개의 인터페이스를 구현하는 클래스로 제한

fun <T> myMax(a: T, b: T): T where T: Number, T: Comparable<T> {
    return if (a > b) a else b
}

fun main() {
    val obj1 = ClassA<HandlerA>()               // 객체 생성 가능
//    val obj2 = ClassA<HandlerB>()               // 범위에 없으므로 에러 발생

    println(myMax(10, 20))
}