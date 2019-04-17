package chap02

import java.io.File
import java.lang.NumberFormatException
import java.nio.file.Files

// 코틀린의 표현식으로서의 try-catch
fun main() {
    val str = readLine()
    val a: Int = try {
        str!!.toInt()
    } catch (e: NumberFormatException) {
        -1
    } finally {
        -2
    }
    println(a)
}

// 코틀린에서 예외는 미확인 예외로 모든곳에 try-catch를 사용할 필요가 없다
fun fileToString(file: File): String {
    // readAllBytes 메소드에서 IOException 을 던지지만 잡지 않아도 컴파일 오류가 없다
    val fileContent = Files.readAllBytes(file.toPath())
    return String(fileContent)
}