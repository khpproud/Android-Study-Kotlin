package chap05

// 충돌하는 이름을 가진 확장 함수
class Worker {
    fun work() = "*열심히 일한다*"
    private fun rest() = "*쉰다*"
}

fun Worker.work() = "*열심히 일하지는 않는다*"

fun <T> Worker.work(t: T) = "*$t 작업중"

fun Worker.rest() = "*비디오 게임 하는중"

fun main() {
    val worker = Worker()

    println(worker.work())

    println(worker.work("Refactoring"))

    println(worker.rest())
}