package ch06_property

// lateinit 을 이용한 지연 초기화하기 예
class Person {
    lateinit var name: String

    fun test() {
        if (!::name.isInitialized) {                // 프로퍼티의 초기화 여부 판단
            println("not initialized!")
        } else {
            println("initialized")
        }
    }
}

fun main() {
    val koo = Person()
    koo.test()
    koo.name = "Koo"                                // 여기서 초기화됨
    koo.test()
    println("name = ${koo.name}")
}