package ch11_coroutine

// Thread 클래스를 손쉽게 사용하도록 함수 만들기
// 람다식을 추가로 만들어 실행
public fun thread(start: Boolean = true, isDaemon: Boolean = false,
                  contextClassLoader: ClassLoader? = null, name: String? = null,
                  priority: Int = -1, block: () -> Unit): Thread {
    val thread = object : Thread() {
        override fun run() {
            block()
        }
    }
    if (isDaemon)                               // 백그라운드 실행 여부
        thread.isDaemon = true
    if (priority > 0)                           // 우선 순위(1: low ~ 5: normal ~ 10: high)
        thread.priority = priority
    if (name != null)                           // 이름
        thread.name = name
    if (contextClassLoader != null)
        thread.contextClassLoader = contextClassLoader
    if (start)
        thread.start()
    return thread
}

fun main() {
    // 스레드의 옵션 변수를 손쉽게 설정 할 수 있음
    thread(start = true) {
        println("Current Threads(Custom function): ${Thread.currentThread()}")
        println("Priority: ${Thread.currentThread().priority}")
        println("Name: ${Thread.currentThread().name}")
        println("isDaemon: ${Thread.currentThread().isDaemon}")
    }
}