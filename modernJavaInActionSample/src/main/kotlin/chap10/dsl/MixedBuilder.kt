package chap10.dsl

import chap10.dsl.model.Order
import chap10.dsl.model.Stock
import chap10.dsl.model.Trade
import chap10.dsl.model.Type
import java.util.*
import java.util.stream.Stream

class MixedBuilder private constructor() {

    companion object {
        fun forCustomer(customer: String, vararg builders: TradeBuilder): Order {
            val order = Order(customer)
            Arrays.stream(builders).forEach { b -> order.addTrade(b.trade) }
            return order
        }

        fun buy(consumer: (TradeBuilder) -> Unit): TradeBuilder {
            return buildTrade(consumer, Type.BUY)
        }

        fun sell(consumer: (TradeBuilder) -> Unit): TradeBuilder {
            return buildTrade(consumer, Type.SELL)
        }

        inline fun buildTrade(consumer: (TradeBuilder) -> Unit, buy: Type): TradeBuilder {
            val tradeBuilder = TradeBuilder(buy)
            consumer.invoke(tradeBuilder)
            return tradeBuilder
        }
    }

    class TradeBuilder(private val type: Type) {
        lateinit var trade: Trade
        lateinit var stock: Stock

        private var quantity: Int = 0

        fun quantity(quantity: Int): TradeBuilder = apply { this.quantity = quantity }

        fun at(price: Double): TradeBuilder = apply {
            trade = Trade(type, stock, quantity, price)
        }

        fun stock(symbol: String): StockBuilder = StockBuilder(this, symbol)
    }

    class StockBuilder(private val builder: TradeBuilder, private val symbol: String) {

        fun on(market: String): TradeBuilder {
            builder.stock = Stock(symbol, market)
            return builder
        }
    }
}