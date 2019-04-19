package chap04

// 코틀린의 정적 함수 선언 및 사용 예
fun foo() {
    println("calling from foo method")
}

// Object(싱글톤) 선언 안에 메소드 또는 변수를 넣음
object Foo {
    fun callFoo() = println("Foo")
    var foo = "foo"
}

// 클래스 이름을 사용하여 클래스의 요서에 접근하려면(자바 같이) companion 키워드를 사용
class SampleClass {
    companion object {
        fun foo() = println("In foo method")

        // companion 키워드 사용 없이 @JvmStatic 어노테이션 추가해도 됨
        @JvmStatic
        fun boo() = println("In boo method")
    }
}

fun main() {
    // 클래스의 인스턴스 만들 필요 없이 사용 가능
    foo()

    Foo.callFoo()

    SampleClass.foo()
    SampleClass.Companion.foo()

    SampleClass.boo()
}