package chap10.dsl.model

import java.util.stream.Collectors.joining

data class Stock(val symbol: String, val market: String)

data class Trade(val type: Type, val stock: Stock, val quantity: Int, val price: Double) {

    fun getValue(): Double {
        return quantity * price
    }
}

data class Order(val customer: String, private val trades: MutableList<Trade> = mutableListOf()) {
    fun addTrade(trade: Trade) = trades.add(trade)
    fun getValue(): Double = trades.stream().mapToDouble(Trade::getValue).sum()

    override fun toString(): String {
        val strTrades = trades.stream().map { t -> " $t" }.collect(joining("\n", "[\n", "\n]"))
        return String.format("Order[customer=%s, trade=%s", customer, strTrades)
    }
}

object Tax {
    fun regional(value: Double) = value * 1.1

    fun general(value: Double) = value * 1.3

    fun surcharge(value: Double) = value * 1.05
}

enum class Type { BUY, SELL }