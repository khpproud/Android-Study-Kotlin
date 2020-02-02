package chap03

import java.util.function.Function

object FunctionSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val list1 = map(listOf("lambdas", "in", "action"), Function { s: String -> s.length })
        println(list1)

        val list2 = map(listOf("action", "of", "lambdas"), String::length)
        println(list2)
    }

    fun <T, R> map(list: List<T>, f: Function<T, R>): List<R> {
        val result = arrayListOf<R>()
        for (t in list) {
            result.add(f.apply(t))
        }
        return result
    }

    // Or
    fun <T, R> map(list: List<T>, f: (T) -> R): List<R> {
        val result = arrayListOf<R>()
        for (t in list) {
            result.add(f.invoke(t))
        }
        return result
    }
}