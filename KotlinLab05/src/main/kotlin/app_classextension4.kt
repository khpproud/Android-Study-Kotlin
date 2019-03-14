package sixteen_one_four

// 같은 이름의 함수 확장
class Test {
    fun sayHello() {
        println("Test... sayHello()")
    }
}

fun Test.sayHello() {
    println("Test extension... sayHello()")
}

fun main() {
    val test = Test()
    test.sayHello()
}