package chap03

import java.util.function.Predicate

object PredicateSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val notEmptyStringPredicate: Predicate<String> = Predicate { s: String -> s.isNotEmpty() }

        val notEmpty: List<String> = filter(
            listOf("", "abc", "", "def", "zzz", ""),
            notEmptyStringPredicate
        )

        println(notEmpty)

        val notEmpty2: List<String> = filter(listOf("", "hoo", "aaa", "", "", "cool")) { s -> s.isNotEmpty() }
        println(notEmpty2)
    }

    // With Predicate
    fun <T> filter(list: List<T>, p: Predicate<T>): List<T> {
        val results = arrayListOf<T>()
        for (t in list) {
            if (p.test(t)) {
                results.add(t)
            }
        }
        return results
    }

    // Or Functional description(In JVM, this converts 'Function1')
    fun <T> filter(list: List<T>, p: (T) -> Boolean): List<T> {
        val results = arrayListOf<T>()
        for (t in list) {
            if (p(t)) {
                results.add(t)
            }
        }
        return results
    }
}