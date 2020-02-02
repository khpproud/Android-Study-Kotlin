package chap07

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask
import java.util.stream.LongStream

class ForkJoinSumCalculator(val numbers: LongArray, val start: Int = 0, val end: Int = numbers.size): RecursiveTask<Long>() {

    companion object {
        const val THRESH_HOLD = 10_000

        fun forkJoinSum(n: Long): Long {
            val numbers = LongStream.rangeClosed(1, n).toArray()
            val task: ForkJoinTask<Long> = ForkJoinSumCalculator(numbers)
            return ForkJoinPool().invoke(task)
        }
    }

    override fun compute(): Long {
        val length = end - start
        if (length <= THRESH_HOLD) {
            return computeSequentially()
        }
        // Create half left task
        val leftTask: ForkJoinSumCalculator = ForkJoinSumCalculator(numbers, start, start + length / 2)
        // Async execution on forked thread
        leftTask.fork()
        // Create half right task
        val rightTask = ForkJoinSumCalculator(numbers, start + length / 2, end)
        // Sync execution
        val rightResult = rightTask.compute()
        // Get result immediately or wait for result
        val leftResult = leftTask.join()
        // If both results are available, sum the results
        return leftResult + rightResult
    }

    private fun computeSequentially(): Long {
        var sum = 0L
        for (i in start until end) {
            sum += numbers[i]
        }
        return sum
    }
}