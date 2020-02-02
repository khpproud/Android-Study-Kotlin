package chap13

import java.util.function.Function

object Letter {
    @JvmStatic
    fun main(args: Array<String>) {
        val addHeader: Function<String, String> = Function(::addHeader)
        val transformationPipeline = addHeader
            .andThen(Function(::checkSpelling))
            .andThen(Function(::addFooter))

        println(transformationPipeline.apply("C++ very good!"))
    }

    fun addHeader(text: String) = "From Simpson, Mario: $text"

    fun addFooter(text: String) = "$text Kind regards"

    fun checkSpelling(text: String) = text.replace(Regex("C\\+\\+"), "Java")
}