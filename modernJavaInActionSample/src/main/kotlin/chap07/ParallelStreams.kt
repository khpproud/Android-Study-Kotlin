package chap07

import java.util.stream.LongStream
import java.util.stream.Stream

object ParallelStreams {

    fun iterativeSum(n: Long): Long {
        var result = 0L
        for (i in 0.. n) {
            result += i
        }
        return result
    }

    fun sequentialSum(n: Long): Long {
        return Stream.iterate(1L, Long::inc).limit(n).reduce(Long::plus).get()
    }

    fun parallelSum(n: Long): Long {
        return Stream.iterate(1L, Long::inc).limit(n).parallel().reduce(Long::plus).get()
    }

    fun rangedSum(n: Long): Long {
        return LongStream.rangeClosed(1, n).reduce(Long::plus).asLong
    }

    fun parallelRangedSum(n: Long): Long {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::plus).asLong
    }

    fun sideEffectSum(n: Long): Long {
        val acc: Accumulator = Accumulator()
        LongStream.rangeClosed(1, n).forEach(acc::add)
        return acc.total
    }

    fun sideEffectParallelSum(n: Long): Long {
        val acc = Accumulator()
        LongStream.rangeClosed(1, n).parallel().forEach(acc::add)
        return acc.total
    }

    class Accumulator {
        var total = 0L

        fun add(value: Long) {
            total += value
        }
    }
}