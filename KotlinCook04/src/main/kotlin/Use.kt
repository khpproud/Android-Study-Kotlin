package chap04

import java.io.FileInputStream

// 코틀린에서 use 키워드 사용 예
// Java 7 try-with-resource
/**
 * try(FileInputStream input = new FileInputStream("file.txt")) {
 *     int data = input.read();
 *     // ~~~
 * }
 * try 블록의 실행이 완료되면 FileInputStream 객체가 자동으로 닫힘
 */

fun main() {
    // use 키워드 사용하면 위와 같은 효과
    FileInputStream("file.txt").use {
        input ->
        var data = input.read()
    }
}


