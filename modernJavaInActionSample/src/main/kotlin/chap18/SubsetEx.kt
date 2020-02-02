package chap18

import java.util.*

fun main() {
    val subs: List<List<Int>> = subsets(listOf(1, 4, 9))
    subs.forEach(::println)
}

fun <T> subsets(l: List<T>): List<List<T>> {
    if (l.isEmpty()) {
        val ans: MutableList<List<T>> = ArrayList()
        ans.add(emptyList())
        return ans
    }

    val first: T = l[0]
    val rest = l.subList(1, l.size)
    val subans = subsets(rest)
    val subans2 = insertAll(first, subans)
    return concat(subans, subans2)
}

fun <T> insertAll(first: T, lists: List<List<T>>): List<List<T>> {
    val result: MutableList<List<T>> = mutableListOf()
    for (l in lists) {
        val copyList = mutableListOf<T>()
        copyList.add(first)
        copyList.addAll(l)
        result.add(copyList)
    }
    return result
}

fun <T> concat(a: List<List<T>>, b: List<List<T>>): List<List<T>> {
    val result: MutableList<List<T>> = mutableListOf(*a.toTypedArray())
    result.addAll(b)
    return result
}

