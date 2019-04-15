package chap05

// 인라인 함수의 예

// 실행시간 측정하는 일반 고차 함수
fun <T> time(body: () -> T): Pair<T, Long> {
    val startTime = System.nanoTime()
    val v = body()
    val endTime = System.nanoTime()
    return v to endTime - startTime
}

// 성능이 최우선인 경우 inline 으로 고차 함수를 표시
inline fun <T> inTime(body: () -> T): Pair<T, Long> {
    val startTime = System.nanoTime()
    val v = body()
    val endTime = System.nanoTime()
    return v to endTime - startTime
}

fun main() {
    /**
     * 컴파일 되면 람다가 할당된 오브젝트로 변환되고 실행은 내부의 invoke 호출
     * val(_, time) = time(object: Function0<Unit> {
     *     override fun invoke() {
     *         Thread.sleep(1000)
     *     }
     * })
     */
    val(_, time) = time {
        Thread.sleep(1000)
    }
    println("time = $time")


    /**
     * 컴파일되면 전체 함수의 실행이 고차 함수의 본문과 람다의 본문으로 대체
     * Call 스택을 거치지 않으므로 실행속도가 빠름(코드는 더 많이 생성될수 있지만)
     */
    val (_, inTime) = inTime {
        Thread.sleep(1000)
    }
    println("inTime = $inTime")

}
