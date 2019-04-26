package chap12

import arrow.syntax.function.curried
import arrow.syntax.function.pipe
import arrow.syntax.function.reverse
import arrow.syntax.function.uncurried
import arrow.syntax.function.invoke

// 커링 : n 파라미터의 함수를 n 함수 호출의 체인으로 변환
// (A, B) -> R => (A) -> (B) -> R
fun main() {
    val strong: (String, String, String) -> String = { body, id, style ->
        "<strong id=\"$id\" style=\"$style\">$body</strong>" }

    val curriedStrong: (style: String) -> (id: String) -> (body: String) -> String =
            strong.reverse().curried()

    val greenStrong: (id: String) -> (body: String) -> String =
            curriedStrong("color: green")

    val uncurriedGreenStrong: (id: String, body: String) -> String =
            greenStrong.uncurried()

    println(greenStrong("movie5")("Green Inferno"))
    println(uncurriedGreenStrong("movie6", "Green hornet"))

    "Fried Green Tomatoes" pipe ("movie7" pipe greenStrong) pipe :: println

    // 커링과 부분 애플리케이션의 차이점
    val strong2: (String, String, String) -> String = { body, id, style ->
        "<strong id=\"$id\" style=\"$style\">$body</strong>" }

    // 커리드
    println(strong2.curried()("Batman Begins")("trilogy1")("color: black"))
    // 가짜 커리드, 부분 애플리케이션(invoke)
    println(strong2("The Dark Knight")("trilogy2")("color: black"))
    // 부분 애플리케이션
    println(strong2(p2 = "trilogy3")(p2 = "color: black")("The Dark Knight rises"))
}