package chap09

import java.util.function.UnaryOperator

object ChainOfResponsibilityMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val p1 = HeaderTextProcessing()
        val p2 = SpellCheckerProcessing()
        p1.successor = p2
        val result1 = p1.handle("Aren't labda really cool?!!")
        println(result1)

        // Java8 Function
        val p3 = UnaryOperator<String> { text -> "From Mario, Lewis and Hamilton: $text" }
        val p4 = UnaryOperator<String> { text -> text.replace("labda".toRegex(), "lambdas") }
        val pipeline = p3.andThen(p4)
        val result2 = pipeline.apply("Aren't labda really cool?!!")
        println(result2)

        // Kotlin lambda style
        val headerProcessing = { text: String -> "From Mario, Lewis and Hamilton: $text"}
        val spellCheckerProcessing = { text: String -> text.replace("labda".toRegex(), "lambdas")}
        val result3 = headerProcessing.invoke("Aren't labda really coll?!!").let { spellCheckerProcessing(it) }
        println(result3)
    }

    abstract class ProcessingObject<T> {
        var successor: ProcessingObject<T>? = null

        fun handle(input: T): T {
            val r = handleWork(input)
            return if (successor != null) {
                return successor!!.handle(r)
            } else r
        }

        protected abstract fun handleWork(input: T): T
    }

    private class HeaderTextProcessing : ProcessingObject<String>() {
        override fun handleWork(input: String): String {
            return "From Mario, Lewis and Hamilton: $input"
        }
    }

    private class SpellCheckerProcessing : ProcessingObject<String>() {
        override fun handleWork(input: String): String {
            return input.replace("labda".toRegex(), "lambdas")
        }
    }
}