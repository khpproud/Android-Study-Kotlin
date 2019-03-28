package seven.let

// let - 스트림 처리에서 표준 함수를 확장 함수 처럼 사용하고 싶을때 선호
fun main() {
   val text = "hello {name}"

    fun correctStyle(text: String) = text.replace("hello", "hello,")

    fun greet(name: String) {
        text.replace("{name}", name)
            .let { correctStyle(it) }
            .capitalize()
            .let { println(it) }
    }

    // 더 간단하게 함수 참조를 인수로 전달 가능
    fun greetRef(name: String) {
        text.replace("{name}", name)
            .let(::correctStyle)
            .capitalize()
            .let(::println)
    }

    greet("reader")
    greetRef("reader")
}
