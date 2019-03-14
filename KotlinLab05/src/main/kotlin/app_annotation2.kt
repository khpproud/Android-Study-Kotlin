package eighteen_two_two

import kotlin.reflect.KClass

// 어노테이션에 데이터 설정
annotation class TestAnnotation(val count: Int)

class Test {
    @TestAnnotation(count = 3)
    fun some() {
        println("some....")
    }
}

// 어노테이션에 설정할 수 있는 데이터 타입 : 다른어노테이션, Class 레퍼런스 타입
annotation class TestAnnotation2(val otherAnno: TestAnnotation, val arg: KClass<*>)

class User

//annotation class TestAnnotation3(val user: User)      // 에러 클래스 객체 타입은 사용할 수 없음

@TestAnnotation(10)
@TestAnnotation2(TestAnnotation(20), String::class)
class Test2 { }

// 상수 타입은 허용
const val myData: Int = 10
@TestAnnotation(myData)
class Test3 { }

fun main() {
    val obj: Test = Test()

    val methods = Test::class.java.methods
    for (method in methods) {
        if (method.isAnnotationPresent(TestAnnotation::class.java)) {
            val annotation = method.getAnnotation(TestAnnotation::class.java)
            val count = annotation.count
            for (i in 1.. count) {
                obj.some()
            }
        }
    }
}