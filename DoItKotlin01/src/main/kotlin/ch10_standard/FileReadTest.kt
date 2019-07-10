package ch10_standard

import java.io.FileReader

// FileReader 로 파일 읽기
fun main() {
    val path = "/Users/patrick/kotlin-study/DoItKotlin01/over_the_rainbow.txt"

    FileReader(path).use { println(it.readText()) }
}