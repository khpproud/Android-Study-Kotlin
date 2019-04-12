package chap02.first

// 타입 앨리어스로 대체
typealias MachineT<T> = (T) -> Unit

fun <T> useMachineT(t: T, machine: MachineT<T>) {
    machine(t)
}

class PrintMachineT<T>: MachineT<T> {
    override fun invoke(p1: T) {
        println(p1)
    }
}

fun main() {
    useMachineT(5, PrintMachineT())

    useMachineT(5, ::println)
    useMachineT(5) { i -> println(i) }
}