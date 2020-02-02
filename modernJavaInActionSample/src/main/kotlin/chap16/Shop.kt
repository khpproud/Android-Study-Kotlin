package chap16

import kotlin.random.Random

data class Shop(val name: String) {
    private val random = Random(name[0].toInt() * name[1].toInt() * name[2].toInt())

    fun getPrice(product: String): String {
        val price = calculatePrice(product)
        val code = Discount.Code.values()[random.nextInt(Discount.Code.values().size)]
        return "$name:$price:$code"
    }

    fun calculatePrice(product: String): Double {
//        delay()
        randomDelay()
        return format(random.nextDouble() * product[0].toInt() + product[1].toInt())
    }
}