package four

// 중첩 클래스 사용 예
// 1. normal
class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
        //fun foo2() = bar                  // 에러, 바깥 클래스에 대한 참조를 가지지 못함
    }
}

// 2. inner 한정자 사용
class Outer2 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar                     // 바깥 클래스의 멤버 접근 가능(private 이어도)
    }
}

fun main() {
    // Outer 클래스의 인스턴스를 만들지 않아도 Nested 클래스의 인스턴스를 만들 수 있음
    val demo = Outer.Nested().foo()     // == 2

    // Outer2 클래스의 인스턴스를 먼저 만들어야 inner 클래스의 인스턴스를 만들 수 있음
    val outer = Outer2()
    val demo2 = outer.Inner().foo()     // == 1
}