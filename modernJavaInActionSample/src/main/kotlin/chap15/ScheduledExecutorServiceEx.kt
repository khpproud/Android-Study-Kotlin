package chap15

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object ScheduledExecutorServiceEx {
    @JvmStatic
    fun main(args: Array<String>) {
        val service = Executors.newScheduledThreadPool(1)

        work1()
        service.schedule(::work2, 5, TimeUnit.SECONDS)

        service.shutdown()
    }

    fun work1() = println("Hello from Work1!")

    fun work2() = println("Hello from Work2!")
}