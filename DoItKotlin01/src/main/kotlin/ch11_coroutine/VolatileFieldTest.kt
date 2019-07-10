package ch11_coroutine

// @Volatile 사용하기 예
@Volatile private var running = false
private var count = 0

fun start() {
    running = true
    thread(start = true) {
        while (running)
            println("${Thread.currentThread()}, count: ${count++}")
    }
}

fun stop() {
    running = false
}

fun main() {
    start()
    start()
    Thread.sleep(50)
    stop()
}