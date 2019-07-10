package ch10_standard

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

// Files 의 write() 메서드를 사용해 파일 생성하기
fun main() {
    val path = "/Users/patrick/kotlin-study/DoItKotlin01/hello.txt"        // 파일을 생성할 경로 지정
    val text = "Hello! Kotlin!\n"

    try {
        Files.write(Paths.get(path), text.toByteArray(), StandardOpenOption.CREATE)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}