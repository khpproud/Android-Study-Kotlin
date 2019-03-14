package sixteen_one_two

// 다형성
open class Super {
    open fun sayHello() {
        println("Super... sayHello()")
    }
}

class Sub: Super() {
    override fun sayHello() {
        println("Sub... sayHello()")
    }
}

fun some(obj: Super) {
    obj.sayHello()
}

fun main() {
    some(Sub())
}