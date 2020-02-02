package chap03

object Sam {

    val r1: Runnable = Runnable { println("Hello lambda") }

    @JvmStatic
    fun main(args: Array<String>) {
        process(r1)
        process(Runnable { println("Hello lambda") })
        /**
         * Belows available Java SAM signature
         * e.g) void process(Runnable r);
         */
//        process { println("This lambda") }

        // or equivalent with Function Descriptor: () -> Unit
        process { println("Hello lambda!") }

    }

    @JvmStatic
    fun process(r: Runnable) {
        r.run()
    }

    fun process(r: () -> Unit) {
        r.invoke()
    }
}

