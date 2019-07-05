package ch06.property

/**
 * 익명 객체는 지역(local)이나 private 정의 영역에서만 자료형으로 사용될 수 있음
 * 만일 익명 객체를 public 함수/속성 의 반환자료형이나 자료형으로 쓴다면 실제 속성은 선언된 속성의 상위 속성이나 없다면 Any 형이 됨
 */
class C {
    // Private function : 반환 자료형은 익명 객체의 자료형이 됨
    private fun foo() = object {
        val x: String = "x"
    }

    // Public function : 반환 자료형은 Any 형이 됨
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
//        val x2 = publicFoo().x                // 오류! Unresolved reference : x
        println("x = $x1")
    }
}

fun main() {
    val c = C()
    c.bar()
}