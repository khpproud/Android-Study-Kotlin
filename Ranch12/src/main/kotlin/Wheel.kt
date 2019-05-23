/**
 * 지연 초기화 속성
 */
class Wheel {
    lateinit var alignment: String

    fun initAlignment() {
        alignment = "GOOD"
    }

    fun printAlignment() {
        // 값이 아니라 참조를 전달하므로 콜론 2개를 붙혀 사용(::)
        if (::alignment.isInitialized) println(alignment)
    }
}