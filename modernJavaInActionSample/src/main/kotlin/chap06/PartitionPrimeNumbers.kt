package chap06

import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Collectors.partitioningBy
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.math.sqrt

object PartitionPrimeNumbers {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Numbers partitioned in prime and non-prime: ${partitionPrimes(100)}")
        println("Numbers partitioned in prime and non-prime: ${partitionPrimeWithCustomCollector(100)}")
        println("Numbers partitioned in prime and non-prime: ${partitionPrimesWithInlineCollector(100)}")
    }

    fun partitionPrimes(n: Int = 100): Map<Boolean, List<Int>> {
        return IntStream.rangeClosed(2, n).boxed()
            .collect(partitioningBy(::isPrimeSqrt))
    }

    fun partitionPrimeWithCustomCollector(n : Int = 100): Map<Boolean, List<Int>> {
        return IntStream.rangeClosed(2, n).boxed()
            .collect(PrimeNumbersCollector())
    }

    // 2 ~ candidate - 1
    private fun isPrimeDumb(candidate: Int): Boolean {
        return IntStream.range(2, candidate)
            .noneMatch { i -> candidate % i == 0 }
    }

    // 2 ~ sqrt(candidate)
    private fun isPrimeSqrt(candidate: Int): Boolean {
        val candidateRoot = sqrt(candidate.toDouble()).toInt()
        return IntStream.rangeClosed(2, candidateRoot)
            .noneMatch { i -> candidate % i == 0 }
    }

    // 2 ~ sqrt(candidate) divided by current prime numbers
    private fun isPrime(primes: List<Int>, candidate: Int): Boolean {
        val candidateRoot: Int = sqrt(candidate.toDouble()).toInt()
//        takeWhile(primes) { i -> i <= candidateRoot }.stream()
//            .noneMatch { i -> candidate % i == 0 }
        return primes.stream()
            .takeWhile { i -> i <= candidateRoot }
            .noneMatch { i -> candidate % i == 0 }
    }

    // Eagerly operated takeWhile
    fun <A> takeWhile(list: List<A>, p: (A) -> Boolean): List<A> {
        list.forEachIndexed { i: Int, item: A ->
            if (!p.invoke(item)) {
                return list.subList(0, i)
            }
        }
        return list
    }

    class PrimeNumbersCollector : Collector<Int, MutableMap<Boolean, MutableList<Int>>, Map<Boolean, List<Int>>> {

        override fun supplier(): Supplier<MutableMap<Boolean, MutableList<Int>>> {
            return Supplier { mutableMapOf(
                true to mutableListOf<Int>(),
                false to mutableListOf()
            ) }
        }

        override fun accumulator(): BiConsumer<MutableMap<Boolean, MutableList<Int>>, Int> {
            return BiConsumer { acc, candidate ->
                acc[isPrime(acc[true]!!.toList(), candidate)]
                    ?.add(candidate)
            }
        }

        override fun combiner(): BinaryOperator<MutableMap<Boolean, MutableList<Int>>> {
            return BinaryOperator { map1, map2 ->
                map1[true]?.addAll(map2[true]!!.toList())
                map1[false]?.addAll(map2[false]!!.toList())
                map1
            }
        }

        override fun finisher(): Function<MutableMap<Boolean, MutableList<Int>>, Map<Boolean, List<Int>>> {
            return Function { acc -> acc.toMap() }
        }

        override fun characteristics(): MutableSet<Collector.Characteristics> {
            return mutableSetOf()
        }
    }

    fun partitionPrimesWithInlineCollector(n: Int): Map<Boolean, MutableList<Int>> {
        return IntStream.rangeClosed(2, n).boxed().collect(
            { mutableMapOf(true to mutableListOf<Int>(), false to mutableListOf()) },
            { acc, candidate -> acc[isPrime(acc[true]!!, candidate)]?.add(candidate) },
            { map1, map2 ->
                map1[true]?.toMutableList()?.addAll(map2[true]!!)
                map1[false]?.toMutableList()?.addAll(map2[false]!!)
            }
        )
    }
}