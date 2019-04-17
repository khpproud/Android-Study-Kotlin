package chap03

// 추상클래스의 예
abstract class Mammal {
    init {
        println("Hey from Mammal")
    }
    abstract fun move(direction: String)

    fun show(y: Int): String {
        return y.toString()
    }
}

class Dog: Mammal() {
    init {
        println("Hey from Dog")
    }

    override fun move(direction: String) {
        println(direction)
    }
}

fun main() {
    var x = Dog()
    x.move("North")
    println(x.show(23))
}