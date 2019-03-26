package four

enum class Color2(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    BLUE(0, 0, 255),
    GRAY(49, 79, 79),
    VIOLET(238, 130, 238);      // 이 세미콜론은 상수 정의와 멤버 정의를 분리하는 역할

    // 열거형 생성자 인수에 대한 유효성 검사 0 ~ 255
    init {
        require(r in 0.. 255)
        require(g in 0.. 255)
        require(b in 0.. 255)
    }

    // 연산자 우선 순위(+ > bit op) 때문에 괄호 필수
    fun rgb() = (r shl 16) + (g shl 8) + b
}

fun printHex(num: Int) {
    println(num.toString(16))
}

fun main() {
    printHex(Color2.BLUE.rgb())
    printHex(Color2.ORANGE.rgb())
    printHex(Color2.GRAY.rgb())
}