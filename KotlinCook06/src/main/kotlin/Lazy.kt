package chap06

// lazy evaluated 되는 리스트 사용 예
fun main() {
    val A = listOf(1, 2, 3, 4)
    // 필터함수는 객체가 정의될 때 실행
    var B = A.filter {
        println("checking $it")
        it % 2 == 0
    }

    // 시퀀스로 변환
    var C = A.asSequence().filter {
        println("checking $it")
        it % 2 == 0
    }

    C.forEach {
        println("printing $it")
    }

    // 시퀀스 사용 예
    val seq = generateSequence(1) {
        it * 2
    }
    seq.take(10).forEach {
        println(it)
    }
}