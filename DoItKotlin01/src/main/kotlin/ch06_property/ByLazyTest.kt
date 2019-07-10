package ch06_property

// by lazy 로 선언된 프로파티 지연 초기화 하기
class LazyTest {
    init {
        println("init block")
    }

    val subject by lazy {
        println("lazy initialized")
        "Kotlin Programming"
    }

    fun flow() {
        println("not initialized!")
        println("subject one : $subject")           // subject 의 초기화 진행
        println("subject two : $subject")           // 이미 초기화된 값 사용
    }
}

fun main() {
    val test = LazyTest()
    test.flow()
}