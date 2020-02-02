package chap13

object Ambiguous {
    @JvmStatic
    fun main(args: Array<String>) {
        C().hello()
    }

    interface A {
        fun hello() = println("Hello from A")
    }

    interface B {
        fun hello() = println("Hello from B")
    }

    class C : B, A {
        override fun hello() {
            super<A>.hello()
        }
    }
}