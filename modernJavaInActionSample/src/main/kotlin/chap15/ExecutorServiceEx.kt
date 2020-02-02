package chap15

import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.Future

object ExecutorServiceEx {
    @JvmStatic
    @Throws(ExecutionException::class, InterruptedException::class)
    fun main(args: Array<String>) {
        val x = 1337

        val executorService = Executors.newFixedThreadPool(2)
        val y: Future<Int> = executorService.submit<Int> { fo(x) }
        val z: Future<Int> = executorService.submit<Int> { go(x) }
        println(y.get() + z.get())

        executorService.shutdown()
    }
}