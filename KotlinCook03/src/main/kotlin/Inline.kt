package chap03

// 인라인 속성 예
// 함수 내용이 호출될 위치에 직접 삽입
class X {
    companion object {
        val CONST_MAX = 3
    }

    var someValue = 3

    // 속성이 inline 으로 선언됨
    inline var valueIsMinedOut: Boolean
    get() = someValue == CONST_MAX
    set(value) {
        println("Value is not set!!")
    }

    // 속성에 backing field 가 있거나 접근자가 backing field 를 참조하는 경우 inline 사용할 수 없음
    var valuesIsMaxedOut: Boolean = true
    get() = field
    set(value) {
        field = value
        println("Value set!!")
    }
}

fun main() {
    var a = X()
    a.valuesIsMaxedOut = false
    println(a.valuesIsMaxedOut)
}
