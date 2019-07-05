package ch05_class
// 주 생성자와 부 생성자 함께 사용하기
class Person2(firstName: String, out: Unit = println("[Primary Constructor] Parameter")) {
    val fName = println("[Property] Person2 fName: $firstName")       // 프로퍼티 할당

    init {
        println("[init] Person2 init block")
    }

    constructor(firstName: String, age: Int, out: Unit = println("[Secondary Constructor] Parameter")) : this(firstName) {
        println("[Secondary Constructor] Body: $firstName, $age")           // 부생성자 본문
    }
}
fun main() {
    val p1 = Person2("Kim", 30)
    println()
    val p2 = Person2("Park")
}