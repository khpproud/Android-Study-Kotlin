@file: JvmName("MyTest")
package eighteen_two_four

// 어노테이션 이용 측 대상 지정
annotation class TestAnnotation
annotation class TestAnnotation2

class Test constructor(@param: TestAnnotation var email: String) {
    @get:[TestAnnotation TestAnnotation2]
    var no: Int = 10

    @property: TestAnnotation
    var name: String = "Park"

    @field: TestAnnotation
    var age: Int = 30

    @setparam: TestAnnotation
    var phone: String = "01001234567"
}

fun @receiver: TestAnnotation Test.myFun() { }