package chap16

fun main() {
    val shop = AsyncShop("BestShop")
    val start = System.nanoTime()
    val futurePrice = shop.getPrice("myPhone")
    val invocationTime = (System.nanoTime() - start) / 1_000_000
    println("Invocation returned after $invocationTime msecs")
    try {
        println("Price is ${futurePrice.get()}")
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
    // Not approved
    val retrievalTime = (System.nanoTime() - start) / 1_000_000
    println("Price returned after $retrievalTime msecs")
}