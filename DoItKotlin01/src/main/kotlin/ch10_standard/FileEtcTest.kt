package ch10_standard

import java.io.File
import java.util.*

// 기타 파일 관련 함수 사용 예
fun main() {
    val path = "/Users/patrick/kotlin-study/DoItKotlin01/over_the_rainbow.txt"
    val pathTo = "/Users/patrick/kotlin-study/DoItKotlin01/over_the_rainbow_copy.txt"

    File(path).copyTo(File(pathTo), true)          // copyTo() 로 파일 복사하기

    File(path).forEachLine { println(it) }                  // 파일 내용 출력하기

    // byte 단위로 읽기
    val bytes = File(path).readBytes()
    println(Arrays.toString(bytes))

    // 줄 단위로 읽기
    val lines = File(path).readLines()
    lines.forEach(::println)

    // 텍스트 단위로 읽기
    val text = File(path).readText()
    println(text)
}