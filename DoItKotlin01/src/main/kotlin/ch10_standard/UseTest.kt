package ch10_standard

import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

// use() 함수 사용 예
fun main() {
    PrintWriter(FileOutputStream("/Users/patrick/kotlin-study/DoItKotlin01/output.txt")).use {
        it.println("hello")
    }

    val file = File("/Users/patrick/kotlin-study/DoItKotlin01/output.txt")
    file.bufferedReader().use {
        println(it.readText())
    }
}