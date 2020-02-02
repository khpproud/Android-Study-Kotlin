package chap15

object CallbackStyleEx {
    @JvmStatic
    fun main(args: Array<String>) {
        val x = 1337
        val result = Result()

        f(x) { y ->
            result.left = y
            println(result.left + result.right)
        }

        g(x) { z ->
            result.right = z
            println(result.left + result.right)
        }
    }

    data class Result(var left: Int = 0, var right: Int = 0)

    inline fun f(x: Int, dealWithResult: (Int) -> Unit) = dealWithResult.invoke(f(x))

    inline fun g(x: Int, dealWithResult: (Int) -> Unit) = dealWithResult.invoke(g(x))
}