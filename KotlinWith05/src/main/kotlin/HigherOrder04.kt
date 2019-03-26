package five.higher

// 주변 코드 명명
fun addLogs(tag: String, f: () -> Unit) {
    println("$tag started")
    val startTime = System.currentTimeMillis()
    f()
    val endTime = System.currentTimeMillis()
    println("$tag finished. It took ${endTime - startTime}.")
}

// 제어 구조의 정의와 사용 예
// 오류를 반환하지 않는 동안 계속 실행
fun repeatUntilError(code: () -> Unit): Throwable {
    while (true) {
        try {
            code()
        } catch (t: Throwable) {
            return t
        }
    }
}

fun main() {
    addLogs("Sleeper") {
        Thread.sleep(1000L)
    }
}