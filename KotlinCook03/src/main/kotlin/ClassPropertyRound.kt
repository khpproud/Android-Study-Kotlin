package chap03.reflection

import kotlin.reflect.full.memberProperties

// 클래스 속성 순회 예(Reflection 이용)
open class Person {
    val isHuman: Boolean = true
}

class Student constructor(var rollNumber: Int, var fullName: String): Person()

fun main() {
    var student = Student(2019, "Lee")
    for (property in Student::class.memberProperties) {
        println("${property.name} = ${property.get(student)}")
    }

    // Student 클래스에만 선언된 필드를 분석하려는 경우 declared 접두가 붙은 멤버 사용
}