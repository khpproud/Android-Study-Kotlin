package chap07

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

// 네트워크로 부터 데이터 읽기
fun main() {
    runBlocking {
        launch {
            val response = URL("https://api.github.com/zen").readText()
            println("${Thread.currentThread().name} => $response")
        }
    }
}