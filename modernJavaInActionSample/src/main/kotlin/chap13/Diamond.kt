package chap13

object Diamond {
    @JvmStatic
    fun main(args: Array<String>) {
        D().hello()
    }

    interface A {
        fun hello() = println("Hello from A")
    }

    interface B : A

    interface C : A

    class D : B, C
}