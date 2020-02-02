package chap16

import java.beans.IntrospectionException
import java.util.concurrent.*

fun main() {
    test()
}

private fun doSomeLongComputation(): Long {
    Thread.sleep(500)
    return 1L
}

private fun doSomethingElse(): Long {
    Thread.sleep(100)
    return 0
}

private fun test() {
    val executor = Executors.newCachedThreadPool()
    val future: Future<Long> = executor.submit<Long> { doSomeLongComputation() }
    doSomethingElse()
    try {
        val result = future.get(1, TimeUnit.SECONDS)
        println(result)
    } catch (e: ExecutionException) {}
    catch (e: IntrospectionException) {}
    catch (e: TimeoutException) {}
    finally {
        executor.shutdown()
    }
}