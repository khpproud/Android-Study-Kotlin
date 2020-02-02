package chap07

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import java.util.stream.LongStream
import java.util.stream.Stream

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = ["-Xms4G", "-Xms4G"])
@Measurement(iterations = 2)
@Warmup(iterations = 2)
class ParallelStreamBenchmark {

    private val N = 10_000_000L

    @Benchmark
    fun iterativeSum(): Long {
        var result = 0L
        for (i in 0.. N) {
            result += i
        }
        return result
    }

    @Benchmark
    fun sequentialSum(): Long {
        return Stream.iterate(1L, Long::inc).limit(N).reduce(0L, Long::plus)
    }

    @Benchmark
    fun parallelSum(): Long {
        return Stream.iterate(1L, Long::inc).limit(N).parallel().reduce(0L, Long::plus)
    }

    @Benchmark
    fun rangedSum(): Long {
        return LongStream.rangeClosed(1, N).reduce(0L, Long::plus)
    }

    @Benchmark
    fun parallelRangedSum(): Long {
        return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::plus)
    }

    @TearDown(Level.Invocation)
    fun tearDown() {
        System.gc()
    }
}