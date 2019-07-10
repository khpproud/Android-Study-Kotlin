package ch10_standard

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

// 파일에 쓰기 다양한 예
fun main() {
    val outString = "안녕하세요!\tHello\rWorld!."                  // 문자열의 구성
    val path = "/Users/patrick/kotlin-study/DoItKotlin01/testFile.txt"

    val file = File(path)
    val printWriter = PrintWriter(file)

    printWriter.println(outString)                              // 파일에 출력
    printWriter.close()                                         // 사용 후 반드시 닫아 주어야 함

//    File(path).printWriter().use { it.println(outString) }    // 다음과 같이 한 줄로 줄일 수 있음

    File(path).bufferedWriter().use { it.write(outString) }     // bufferedWriter 로 쓰기

    file.writeText(outString)                                   // 코틀린의 writeText() 함수 사용
    file.appendText("\nDo great work!")

    FileWriter(path, true).use { it.write(outString) }  // FileWriter 사용
}