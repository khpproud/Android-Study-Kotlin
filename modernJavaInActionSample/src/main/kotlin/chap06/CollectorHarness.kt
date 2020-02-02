package chap06

import java.util.function.Consumer

object CollectorHarness {
    @JvmStatic
    fun main(args: Array<String>) {
//        println("Partitioning done in " +
//                "${execute { num: Int -> PartitionPrimeNumbers.partitionPrimes(num) }} msecs")
        println("Partitioning done in " +
                "${execute { num -> PartitionPrimeNumbers.partitionPrimeWithCustomCollector(num) }} msecs")
    }

    inline fun execute(primePartition: (Int) -> Unit): Long {
        var fastest = Long.MAX_VALUE
        repeat(10) {
            val start = System.nanoTime()
            primePartition.invoke(100_000)
            val duration = (System.nanoTime() - start) / 1_000_000  // convert to ms
            if (duration < fastest) {
                fastest = duration
            }
            println("Done is $duration ms")
        }
        return fastest
    }
}