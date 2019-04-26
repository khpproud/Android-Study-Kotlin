package chap12

import arrow.syntax.function.*

// 원래의 스플리터 기능은 직접적으로 warehouse와 accounting 함수를 호출했으므로 유연하지 않았음
// partialSplitter 함수는 두 함수를 파라미터로 받아들이는 것으로 이 문제를 해결
fun partialSplitter(billAndOrder: Pair<Bill, PickingOrder>?,
                    warehouse: (PickingOrder) -> Unit, accounting: (Bill) -> Unit) {
    if (billAndOrder != null) {
        warehouse(billAndOrder.second)
        accounting(billAndOrder.first)
    }
}

// 부분 애플리케이션으로 함수 생성
fun main() {
    val strong: (String, String, String) -> String = { body, id, style ->
        "<strong id=\"$id\" style=\"$style\">$body</strong>" }
    // 명시적 => partially3 style에 적용
    val redStrong: (String, String) -> String = strong.partially3("font: red")
    // 암시적(invoke 사용)
    val blueStrong: (String, String) -> String = strong(p3 = "font: blue")

    println(redStrong("Red Sonja", "movie1"))
    println(blueStrong("Deep Blue Sea", "movie2"))

    val splitter: (billAndOrder: Pair<Bill, PickingOrder>?) -> Unit =
            ::partialSplitter.partially2 {/* p1 = */ order -> println("테스트 $order") } (p2 = ::accounting)

    val salesSystem: (quote: Quote) -> Unit = ::calculatePrice andThen ::filterBills forwardCompose splitter
    salesSystem(Quote(20.0, "Foo", "Shoes", 1))
    salesSystem(Quote(20.0, "Bar", "Shoes", 200))
    salesSystem(Quote(2000.0, "Foo", "Motorbike", 1))

    // 바인딩 - 부분 애플리케이션의 특별한 경우: 바인딩을 통해 T 파라미터를 (T) -> R 함수로 전달하지만 실행없이 () -> R 함수를 효과적으로 반환
    val footer: (String) -> String = { content -> "<footer>$content</footer>"}
    // partially1을 위한 앨리어스
    val fixFooter: () -> String = footer.bind("Functional kotlin - 2019")
    println(fixFooter())
}