package chap07

import java.io.File

// InputReader 를 이용해 파일 읽기
fun main() {
    val inputStream = File("sample.txt").inputStream()
    var inputString = inputStream.reader().use { it.readText() }
    println(inputString)

    // 파일로 부터 reader를 직접 생성
    inputString = File("sample.txt").reader().use {
        it.readText()
    }
    println(inputString)

    // 줄 단위로 읽을 때 useLines()
    val listOfLines = mutableListOf<String>()
    File("sample.txt").reader().useLines { lines ->
        lines.forEach {
            listOfLines.add(it)
        }
    }
    listOfLines.forEach { println("$ $it") }
}