package chap03

import java.util.function.Consumer

object ConsumerSample {
    @JvmStatic
    fun main(args: Array<String>) {
        forEach(listOf(1, 2, 3, 4, 5), Consumer(::println))

        forEach(listOf("a", "b", "c", "d"), ::println)
    }

    fun <T> forEach(list: List<T>, c: Consumer<T>) {
        for (t in list) {
            c.accept(t)
        }
    }

    fun <T> forEach(list: List<T>, c: (T) -> Unit) {
        for (t in list) {
            c(t)
        }
    }
}