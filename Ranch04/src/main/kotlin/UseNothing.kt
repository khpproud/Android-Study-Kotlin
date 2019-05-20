/**
 * TODO 의 유용한 사용 예
 */
fun shouldReturnAString(): String {
    var point = 100
    var simple = when (point) {
        100 -> "Best"
        in 90.. 99 -> "Better"
        else -> "Not Good"
    }
    TODO("문자열을 반환하는 코드를 여기에 구현해야 함")
    println("Unreachable code!!!")
}

/**
 * 백틱(backtick) 기호를 사용한 함수 이름 예
 * 주로 코틀린에서는 예약어인데 자바에서 메서드 이름으로 사용되는 "is" 같은 경우
 * 테스트 코드를 작성할 때 함수의 용도를 더 쉽게 파악하기 위해 사용
 */
fun `use backtick word for function call`() {
    println("(``) used backtick...")
}

fun main() {
    `use backtick word for function call`()
    shouldReturnAString()
}
