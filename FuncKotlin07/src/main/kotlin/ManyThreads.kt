package chap07

import java.util.concurrent.Executors

// 일반적인 개발머신에서 100개 스레드는 쉽게 다룸
fun main() {
//    val threads = List(100) {
//        thread {
//            Thread.sleep(100)
//            print(".")
//        }
//    }
//    threads.forEach(Thread:join)

    // Executor 사용(스레드 관리와 재사용의 추상화된 클래스)
    // 내부적으로 1024개의 스레드 풀을 가지도록
    val executor = Executors.newFixedThreadPool(1024)
    // 10,000 태스크 실행
    repeat(10000) {
        executor.submit {
            Thread.sleep(100)
            print('.')
        }
    }
    // Executor 종료(대기 중인 모든작업이 수행된 뒤 종료)
    executor.shutdown()
}