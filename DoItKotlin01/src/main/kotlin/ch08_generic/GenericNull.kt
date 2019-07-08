package ch08_generic

// 형식 매개변수에 null 이 가능한 제네릭 클래스
class GenericNull<T> {              // 기본적으로 null 이 허용되는 형식 매개변수
    fun equalityFunc(arg1: T, arg2: T) {
        println(arg1?.equals(arg2))
    }
}

fun main() {
    val obj = GenericNull<String>()                 // non-null 로 선언됨
    obj.equalityFunc("Hello", "Hello")  // null 이 허용되지 않음

    val obj2 = GenericNull<Int?>()                  // null 이 가능한 형식으로 선언됨
    obj2.equalityFunc(null, 10)
}