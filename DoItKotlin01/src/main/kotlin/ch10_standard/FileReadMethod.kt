package ch10_standard

import java.io.*
import java.lang.StringBuilder

// 자바 읽기 코드의 단순 변환
fun main() {
    val path = "/Users/patrick/kotlin-study/DoItKotlin01/over_the_rainbow.txt"

    // 자바 방식의 파일 읽기
//    val file = File(path)
//    val inputStream: InputStream = file.inputStream()
//    val inputStreamReader = InputStreamReader(inputStream)
//    val sb = StringBuilder()
//    var line: String?
//    val br = BufferedReader(inputStreamReader)
//    try {
//        line = br.readLine()
//        while (line != null) {
//            sb.append(line, '\n')
//            line = br.readLine()
//        }
//        println(sb)
//    } catch (e : IOException) {
//        e.printStackTrace()
//    } finally {
//        br.close()
//    }

    // 코틀린 방식으로 작성
//    val file = File(path)
//    val inputStream: InputStream = file.inputStream()
//    val text = inputStream.bufferedReader().use { it.readText() }
//    println(text)

    // BufferedReader 로만 구성해 더 간단하게 작성
//    val bufferedReader: BufferedReader = File(path).bufferedReader()
//    val inputString = bufferedReader.use { it.readText() }
//    println(inputString)

    // 줄단위로 처리하기 위해 use() 대신 useLines() 사용
//    val bufferedReader = File(path).bufferedReader()
//    val lineList = mutableListOf<String>()
//    bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
//    lineList.forEach { println("> $it")}

    // BufferedReader 까지 생략하고 파일을 직접 사용
    val lineList = mutableListOf<String>()
    File(path).useLines { lines -> lines.forEach { lineList.add(it) } }
    lineList.forEach { println("> $it") }
}