package chap07

import java.io.File

// BufferedReader 를 이용해 파일 읽기
fun main() {
    var inputString = File("sample.txt").bufferedReader().use {
        it.readText()
    }
    println(inputString)

    // 파일을 줄 단위로 처리
    val listOfLines = mutableListOf<String>()
    File("sample.txt").bufferedReader().useLines {
        lines -> lines.forEach {
            var x = "> (${it.length}) $it"
            listOfLines.add(x)
        }
    }
    listOfLines.forEach { println(it) }

    // 파일의 InputStream 으로 부터 BufferedReader 를 생성해 줄 단위로 파일 내용 읽기
    val listOfLines2 = mutableListOf<String>()
    val inputStream = File("sample.txt").inputStream()
    inputStream.bufferedReader().useLines {lines ->
        lines.forEach {
            var x = "> (${it.length}) $it"
            listOfLines2.add(x)
        }
    }
    listOfLines2.forEach { println(it) }

    // readLine()을 사용한다면 스트림을 직접 닫아주어야 한다
    val listOfLines3 = mutableListOf<String>()
    val reader = File("sample.txt").bufferedReader()

    while (true) {
        var line: String? = reader.readLine() ?: break
        listOfLines3.add("> $line")
    }

    listOfLines3.forEach { println(it) }

    // close() 명시적으로 호출해 주어야 함
    reader.close()
}