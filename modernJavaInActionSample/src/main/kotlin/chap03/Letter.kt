package chap03

import java.util.function.Function

object Letter {
    @JvmStatic
    fun main(args: Array<String>) {
        val addFunction: Function<String, String> = Function(::addHeader)
        val transformationPipeline =
            addFunction.andThen(::checkSpelling).andThen(::addFooter).andThen(String::capitalize)
        println(transformationPipeline.apply("labda and labda and lambda rules!!!"))
    }

    fun addHeader(text: String): String = "from John and Mike: $text"

    fun addFooter(text: String): String = "$text Kind regards."

    fun checkSpelling(text: String): String = text.replace("labda", "lambda")
}