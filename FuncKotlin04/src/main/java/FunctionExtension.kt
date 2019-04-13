package chap04

import java.util.regex.Pattern

// 확장 함수의 예

// 문자열의 단어를 셈
fun countWords1(text: String): Int {
    return text.trim()
            .split(Pattern.compile("\\s+"))
            .size
}

// 확장 함수로 정의
fun String.countWords(): Int {
    return trim()
            .split(Pattern.compile("\\s+"))
            .size
}

fun main() {
    val counts = "This is many word".countWords()
    println("count words : $counts")
}