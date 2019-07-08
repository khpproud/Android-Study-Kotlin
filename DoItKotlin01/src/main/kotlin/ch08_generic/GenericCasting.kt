package ch08_generic

// 제네릭 클래스의 자료형 변환하기
open class Parent

class Child : Parent()

class Cup<T>

fun main() {
    val obj1: Parent = Child()              // Parent 형식의 obj1은 Child 의 자료형으로 변활될 수 있음
//    val obj2: Child = Parent()              // 오류! 자료형 불일치

//    val obj3: Cup<Parent> = Cup<Child>()    // 오류! 자료형 불일치
//    val obj4: Cup<Child> = Cup<Parent>()    // 오류! 자료형 불일치

    val obj5 = Cup<Child>()
    val obj6: Cup<Child> = obj5


}