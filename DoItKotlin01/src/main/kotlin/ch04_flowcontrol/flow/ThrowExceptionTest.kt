package ch04_flowcontrol.flow

// throw 를 사용해 예외 발생 시키기
fun main() {
    var amount = 600

    try {
        amount -= 100
        checkAmount(amount)
    } catch (e: Exception) {
        println(e.message)
    }
    println("amount: $amount")
}

fun checkAmount(amount: Int) {
    if (amount < 1000) {
        throw Exception("잔고가 $amount 으로 1000 이하입니다!")
    }
}