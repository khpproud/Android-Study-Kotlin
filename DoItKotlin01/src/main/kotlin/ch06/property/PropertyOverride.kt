package ch06.property

// 프로퍼티의 오버라이딩
open class First {
    open val x: Int = 0             // 오버라이딩 가능
        get() {
            println("First x")
            return field
        }
    val y: Int = 0                  // open 키워드가 없으므로 final
}

class Second : First() {
    override var x: Int = 0         // 오버라이딩 시 val -> var 는 변경 가능 var -> val 은 변경 불가능
        get() {
            println("Second x")
            return field + 3
        }
}

fun main() {
    val second = Second()
    println(second.x)
    println(second.y)
}