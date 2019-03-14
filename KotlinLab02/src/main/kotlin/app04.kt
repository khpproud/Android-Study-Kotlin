fun sum(a: Int, b: Int): Int {
    var sum = 0
    fun calSum() {
        for (i in a.. b) {
            sum += i
        }
    }
    calSum()
    return sum
}

fun some(a: Int, b: Int) = a + b

fun sayHello(name: String?) {
    if (name == null)
        println("Hello!! Ghost")
    else
        println("Hello!! $name")
}

// 기본 인수 사용
fun sayHello2(name: String = "Ghost") {
    println("Hello!! $name")
}

// 명명된 인수(named argument)
fun sayHello3(name: String = "Ghost", no: Int) {
    println("Hello!! $name : $no")
}

fun main() {
    val result = sum(1, 10)
    println(result)

    println(some(2, 13))

    sayHello(null)
    sayHello("Park")

    sayHello2()
    sayHello2("Park")

    sayHello3("Lee", 10)
    sayHello3(no = 10)
    sayHello3(name = "Kim", no = 20)
}