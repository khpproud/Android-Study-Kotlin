package chap12

import arrow.core.compose
import arrow.syntax.function.andThen
import arrow.syntax.function.forwardCompose
import java.util.*

// 애로우의 함수 합성 예
typealias SF = (String) -> String

val strong: SF = { body -> "<strong>$body</strong>" }
val p: SF = { body -> "<p>$body</p>" }
val span: SF = { body -> "<span>$body</span>" }
val div: SF = { body -> "<div>$body</div>" }
val randomNumbers: () -> String = {
    if (Random().nextInt() % 2 == 0) {
        "Foo"
    } else {
        "Bar"
    }
}

// 채널 파이프라인 예제를 함수 합성으로 재작성
data class Quote(val value: Double, val client: String, val item: String, val quantity: Int)

data class Bill(val value: Double, val client: String)

data class PickingOrder(val item: String, val quantity: Int)

fun calculatePrice(quote: Quote) = Bill(quote.value * quote.quantity, quote.client) to
        PickingOrder(quote.item, quote.quantity)

fun filterBills(billAndOrder: Pair<Bill, PickingOrder>): Pair<Bill, PickingOrder>? {
    val (bill, _) = billAndOrder
    return if (bill.value >= 100)
        billAndOrder
    else
        null
}

fun warehouse(order: PickingOrder) = println("오더 처리중 = $order")

fun accounting(bill: Bill) = println("처리중 = $bill")

fun splitter(billAndOrder: Pair<Bill, PickingOrder>?) {
    if (billAndOrder != null) {
        warehouse(billAndOrder.second)
        accounting(billAndOrder.first)
    }
}

fun main() {
    val divStrong: SF = div compose strong
    // divStrong은 다음과 같다
    val divStrong2: SF = { body -> "<div><strong>$body</strong></div>" }

    val spanP: SF = p forwardCompose span
    val randomStrong: () -> String = randomNumbers andThen strong

    val body = "Hello Composition World!!!"
    println(divStrong(body))
    println(spanP(body))
    println(randomStrong())

    // (Quote) -> Unit => 빌딩 블록으로 다른 함수를 사용해 구성
    val salesSystem: (Quote) -> Unit = ::calculatePrice andThen ::filterBills forwardCompose ::splitter
    salesSystem(Quote(20.0, "Foo", "Shoes", 1))
    salesSystem(Quote(20.0, "Bar", "Shoes", 200))
    salesSystem(Quote(2000.0, "Foo", "Motorbike", 1))

}