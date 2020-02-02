package chap03

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object ExecuteAround {
    private const val FILE_NAME = "src/main/resources/chap03/data.txt"

    @JvmStatic
    fun main(args: Array<String>) {
        println(processFileLimited())

        println("------")

        // Kotlin anonymous function style
        val oneLine =
            processFile(object : BufferedReaderProcessor {
                override fun process(b: BufferedReader): String {
                    return b.readLine()
                }
            })
        println(oneLine)

        // Kotlin high order function style
        val twoLine = processFile { br -> br.readLine() + br.readLine() }
        println(twoLine)
    }

    @Throws(IOException::class)
    fun processFileLimited(): String =
        BufferedReader(FileReader(FILE_NAME)).use { br -> return br.readLine() }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        @Throws(IOException::class)
        fun process(b: BufferedReader): String
    }

    @Throws(IOException::class)
    fun processFile(p: BufferedReaderProcessor): String =
        BufferedReader(FileReader(FILE_NAME)).use { br ->
            return p.process(br)
        }

    @Throws(IOException::class)
    fun processFile(p: (BufferedReader) -> String): String =
        BufferedReader(FileReader(FILE_NAME)).use { br ->
            return p(br)
        }
}