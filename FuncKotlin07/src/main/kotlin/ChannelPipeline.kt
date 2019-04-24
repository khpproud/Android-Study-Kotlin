package chap07


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.coroutines.CoroutineContext

// 채널 파이프 라인 예
// 모델
data class Quote(val value: Double, val client: String, val item: String, val quantity: Int)

data class Bill(val value: Double, val client: String)

data class PickingOrder(val item: String, val quantity: Int)

// 채널에서 Quote(따옴표)를 받아 Pair<Bill, PickingOrder> 로 변환
fun calculatePriceTransformer(coroutineContext: CoroutineContext, quoteChannel: ReceiveChannel<Quote>) =
        GlobalScope.produce(coroutineContext) {
            for (quote in quoteChannel) {
                send(Bill(quote.value * quote.quantity, quote.client) to
                PickingOrder(quote.item, quote.quantity))
            }
        }

// cheapBillFilter 함수는 100 미만의 bill 값을 필터링
fun cheapBillFilter(coroutineContext: CoroutineContext, billChannel: ReceiveChannel<Pair<Bill, PickingOrder>>) =
        GlobalScope.produce(coroutineContext) {
            billChannel.consumeEach { (bill, order) ->
                if (bill.value >= 100) {
                    send(bill to order)
                } else {
                    println("청구된 bill = $bill")
                }
            }
        }

// splitter는 Pair<Bill, PickingOrder>를 자신의 채널로 나눔
suspend fun splitter(filteredChannel: ReceiveChannel<Pair<Bill, PickingOrder>>, accountingChannel: SendChannel<Bill>,
                     warehouseChannel: SendChannel<PickingOrder>) = GlobalScope.launch {
    filteredChannel.consumeEach { (bill, order) ->
        accountingChannel.send(bill)
        warehouseChannel.send(order)
    }
}

// accoutingEndpoint 와 warehouseEndpoint 출력으로 각각의 메시지 처리
suspend fun accountingEndpoint(accountingChannel: ReceiveChannel<Bill>) = GlobalScope.launch {
    accountingChannel.consumeEach { bill -> println("bill 처리 = $bill") }
}

suspend fun warehouseEndpoint(warehouseChannel: ReceiveChannel<PickingOrder>) = GlobalScope.launch {
    warehouseChannel.consumeEach { order -> println("작업 처리 = $order") }
}

// 세일즈 시스테을 조립하고 테스트
fun main() = runBlocking {
    val quoteChannel = Channel<Quote>()
    val accountingChannel = Channel<Bill>()
    val warehouseChannel = Channel<PickingOrder>()

    val transformerChannel = calculatePriceTransformer(coroutineContext, quoteChannel)
    val filteredChannel = cheapBillFilter(coroutineContext, transformerChannel)

    splitter(filteredChannel, accountingChannel, warehouseChannel)

    warehouseEndpoint(warehouseChannel)
    accountingEndpoint(accountingChannel)

    launch(coroutineContext) {
        quoteChannel.send(Quote(20.0, "Foo", "Shoes", 1))
        quoteChannel.send(Quote(20.0, "Bar", "Shoes",200))
        quoteChannel.send(Quote(2000.0, "Foo", "Motorbike", 2))
    }
    delay(1000)
    coroutineContext.cancelChildren()
}