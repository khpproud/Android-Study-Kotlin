package chap15

object ThreadEx {

    @JvmStatic
    @Throws(InterruptedException::class)
    fun main(args: Array<String>) {
        val x = 1337
        val result = Result()

        val t1 = Thread { result.left = f(x) }
        val t2 = Thread { result.right = g(x) }
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        println(result.left + result.right)
    }

    data class Result(var left: Int = 0, var right: Int = 0)
}