package ch11_coroutine

// 기본 스레드 사용 예
// Thread 클래스를 상속받아 구현
class SimpleThread: Thread() {
    override fun run() {
        println("Current Threads: ${currentThread()}")
    }
}

// Runnable 인터페이스로부터 run() 메서드 구현
class SimpleRunnable: Runnable {
    override fun run() {
        println("Current Threads: ${Thread.currentThread()}")
    }
}

fun main() {
    val thread = SimpleThread()
    thread.start()

    val runnable = SimpleRunnable()
    val thread1 = Thread(runnable)
    thread1.start()

    // 익명 객체 사용
    object : Thread() {
        override fun run() {
            println("Current Threads(object): ${currentThread()}")
        }
    }.start()

    // Runnable 을 전달하는 람다식
    Thread {
        println("Current Threads(lambda): ${Thread.currentThread()}")
    }.start()
}