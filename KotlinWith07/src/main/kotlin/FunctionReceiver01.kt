package seven.receiver

// 수신자가 있는 함수 리터럴 사용 예
lateinit var power: Int.(Int) -> Int

fun main() {
    power = { n -> (1.. n).fold(1) {acc, _ -> this * acc} }
    println(power(2, 10))                                           //2 ^ 10 = 1024

    // 익명 확장 함수로도 수신자를 정의할 수 있음
    power = fun Int.(n: Int) = (1.. n).fold(1) { acc, _ -> this * acc }

    // 수신자가 있는 함수 형식은 수신자 형식의 메소드처럼 사용 가능
    val result = 10.power(3)                                    // 10 ^ 3 = 1000
    println(result)
}