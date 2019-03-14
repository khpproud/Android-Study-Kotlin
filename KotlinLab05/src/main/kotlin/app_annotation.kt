package eighteen_two_one

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

// 어노테이션 선언
annotation class TestAnnotation

annotation class TestAnnotation2 { }

// 어노테이션 정보 활용
class Test {
    @TestAnnotation
    fun myFun1() { }

    fun myFun2() { }
}

// 생성자, 프로퍼티, 프로퍼티의 getter/setter 에 어노테이션 적용
class Test2 @TestAnnotation constructor() {
    @TestAnnotation
    val myVal: Int = 10

    var myVar: Int = 10
        @TestAnnotation
        set(value) {
            field = value
        }
}

fun main() {
    //val obj: TestAnnotation = TestAnnotation() // 어노테이션 클래스는 객체 생성 불가

    val methods = Test::class.java.methods

    for (method in methods) {
        if (method.isAnnotationPresent(TestAnnotation::class.java)) {
            println("${method.name} function has TestAnnotation annotation...")
        }
    }

    val constructors = Test2::class.java.declaredConstructors
    for (constructor in constructors) {
        if (constructor.isAnnotationPresent(TestAnnotation::class.java)) {
            println("${constructor.name} constructor exist...")
        }
    }

    val fields = Test2::class.java.fields
    for (field in fields) {
        if (field.isAnnotationPresent(TestAnnotation::class.java)) {
            println("${field.name} field exist...")
        }
    }

}