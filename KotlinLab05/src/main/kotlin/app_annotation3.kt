package eighteen_two_three

// 어노테이션 옵션
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class TestAnnotation

@TestAnnotation
class Test {
    //@TestAnnotation constructor(no: Int){ }   // 에러 옵션에 생성자 지정 안함
    //@TestAnnotation                           // 에러 옵션에 프로퍼티 지정 안함
    val myVal: Int = 10

    @TestAnnotation
    fun myFun(@TestAnnotation no: Int) {
        val result = @TestAnnotation if (no > 10) {
            10
        } else {
            0
        }
    }
}
