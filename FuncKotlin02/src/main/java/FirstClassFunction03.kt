package chap02.first

interface Machine<T> {
    fun process(product: T)
}

fun <T> useMachine(t: T, machine: Machine<T>) {
    machine.process(t)
}

class PrintMachine<T>: Machine<T> {
    override fun process(product: T) {
        println(product)
    }
}

fun main() {
    useMachine(5, PrintMachine())
    // 아래와 동일
    useMachine(5, object : Machine<Int> {
        override fun process(product: Int) {
            println(product)
        }
    })
}