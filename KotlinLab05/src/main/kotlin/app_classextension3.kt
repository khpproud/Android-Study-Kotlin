package sixteen_one_three

open class Super

class Sub: Super()

fun Super.sayHello() {
    println("Super... sayHello()")
}
fun Sub.sayHello() {
    println("Sub... sayHello()")
}

fun some(obj: Super) {
    obj.sayHello()
}

fun main() {
    some(Sub())
}