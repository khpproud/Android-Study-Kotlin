package ch07_variable_class

// 호출 연산자 invoke 오버로딩
class Manager {
    operator fun invoke(value: String) = println("value = $value")
}

fun main() {
    val manager = Manager()
    manager("Do something!!")
}