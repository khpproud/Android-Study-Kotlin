package chap07

object ParallelStreamHarness {
    @JvmStatic
    fun main(args: Array<String>) {
        println(
            "Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "Sequential Sum done in: " + measurePerf(
                ParallelStreams::sequentialSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "Parallel forkJoinSum done in: " + measurePerf(
                ParallelStreams::parallelSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "Range forkJoinSum done in: " + measurePerf(
                ParallelStreams::rangedSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "Parallel range forkJoinSum done in: " + measurePerf(
                ParallelStreams::parallelRangedSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "ForkJoin sum done in: " + measurePerf(
                { ForkJoinSumCalculator.forkJoinSum(10_000_000L) },
                10_000_000L
            ) + " msecs"
        )
        println(
            "SideEffect sum done in: " + measurePerf(
                ParallelStreams::sideEffectSum,
                10_000_000L
            ) + " msecs"
        )
        println(
            "SideEffect parallel sum done in: " + measurePerf(
                ParallelStreams::sideEffectParallelSum,
                10_000_000L
            ) + " msecs"
        )
    }

    private inline fun <T, R> measurePerf(f: (T) -> R, input: T): Long {
        var fastest = Long.MAX_VALUE
        repeat(10) {
            val start = System.nanoTime()
            val result = f.invoke(input)
            val duration = (System.nanoTime() - start) / 1_000_000
            println("Result: $result")
            if (duration < fastest) {
                fastest = duration
            }
        }
        return fastest
    }
}