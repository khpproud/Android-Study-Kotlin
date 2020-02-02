package chap16

object ExchangeService {
    const val DEFAULT_RATE = 1.35

    enum class Money(val rate: Double) {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);
    }

    fun getRate(source: Money, destination: Money): Double {
        return getRateWithDelay(source, destination)
    }

    private fun getRateWithDelay(source: Money, destination: Money): Double {
        delay()
        return destination.rate / source.rate
    }
}