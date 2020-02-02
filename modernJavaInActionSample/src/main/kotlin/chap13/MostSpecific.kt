package chap13

object MostSpecific {
    @JvmStatic
    fun main(args: Array<String>) {
        C().hello()     // B
        E().hello()     // B
        G().hello()     // F
    }

    interface A {
        fun hello() = println("Hello from A")
    }

    interface B : A {
        override fun hello() = println("Hello from B")
    }

    class C : B, A

    open class D : A

    class E : D(), B, A

    open class F : B, A {
        override fun hello() {
            println("Hello from F")
        }
    }

    class G : F(), B, A
}