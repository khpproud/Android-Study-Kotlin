package chap12

import arrow.syntax.function.partially3
import arrow.syntax.function.pipe
import arrow.syntax.function.pipe3
import arrow.syntax.function.reverse

// 리버스 : 모든 함수를 받고 역순으로 파라미터와 함께 반환
fun main() {
    val strong: (String, String, String) -> String = { body, id, style ->
        "<strong id=\"$id\" style=\"$style\">$body</strong>" }
    val redStrong: (String, String) -> String = strong.partially3("font: red")

    println(redStrong("Red Sonja", "movie1"))
    println(redStrong.reverse()("movie2", "The Hunt for Red October"))

    // 파이프 : T 값으 받고 (T) -> R 함수를 호출
    val strong2: (String) -> String = { body -> "<strong>$body</strong>" }

    "From a pipe".pipe(strong2).pipe(::println)
    "From a pipe" pipe strong2 pipe ::println
    println(strong2("From a pile"))

    splitter(filterBills(calculatePrice(Quote(20.0, "Foo", "Shoes", 1))))
    Quote(20.0, "Foo", "Shoes", 1) pipe ::calculatePrice pipe ::filterBills pipe :: splitter

    val strong3: (String, String, String) -> String = { body, id, style ->
        "<strong id=\"$id\" style=\"$style\">$body</strong>" }

    val redStrong2: (String, String) -> String = "color: red" pipe3 strong.reverse()
    redStrong2("movie3", "Three colors: Red") pipe ::println

}