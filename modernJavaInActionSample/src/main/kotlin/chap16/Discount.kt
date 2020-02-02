package chap16

object Discount {

    enum class Code(val percentage: Int) {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

    }

    fun applyDiscount(quote: Quote): String {
        return quote.shopName + " price is " + apply(quote.price, quote.discountCode)
    }

    private fun apply(price: Double, code: Code): Double {
        delay()
        return format(price * (100 - code.percentage) / 100)
    }
}