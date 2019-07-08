package ch07_variable_class

// 중첩 클래스 : 자바에서 정적 클래스 처럼 객체 생성없이 사용가능하나 외부 클래스의 프로퍼티, 메서드 접근 불가
class Outer {
    val ov = 5
    class Nested {
        val nv = 10
        fun greeting() = "[Nested] Hello! $nv"               // 외부의 ov 에는 접근 불가
        fun accessOuter() {                                         // 컴패니언 객체는 접근 가능
            println(country)
            getSomething()
        }
    }
    fun outside() {
        val msg = Nested().greeting()                        // 객체 생성 없이 중첩 클래스의 메서드 접근
        println("[Outer] : $msg, ${Nested().nv}")                   // 중첩클래스의 프로퍼티 접근
    }

    companion object {                                              // 컴패니언 객체는 static 처럼 접근 가능
        const val country = "Korea"
        fun getSomething() = println("Get something...")
    }
}

fun main() {
    // static 처럼 객체 생성 없이 사용
    val output = Outer.Nested().greeting()
    println(output)

//    Outer.outside()                                                 // 오류! 외부 클래스의 메서드는 객체 생성 후 사용
    val outer = Outer()
    outer.outside()

    Outer.Nested().accessOuter()
}