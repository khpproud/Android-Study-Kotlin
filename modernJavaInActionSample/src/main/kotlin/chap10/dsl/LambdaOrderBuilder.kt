package chap10.dsl

import chap10.dsl.model.Order
import chap10.dsl.model.Stock
import chap10.dsl.model.Trade
import chap10.dsl.model.Type
import java.util.function.Consumer

class LambdaOrderBuilder private constructor() {

    private lateinit var order: Order

    companion object {
        fun order(consumer: Consumer<LambdaOrderBuilder>): Order {
            val builder = LambdaOrderBuilder()
            consumer.accept(builder)
            return builder.order
        }
    }

    fun forCustomer(customer: String) {
        this.order = Order(customer)
    }

    fun buy(consumer: Consumer<TradeBuilder>) {
        trade(consumer, Type.BUY)
    }

    fun sell(consumer: Consumer<TradeBuilder>) {
        trade(consumer, Type.SELL)
    }

    private fun trade(consumer: Consumer<TradeBuilder>, type: Type) {
        val builder = TradeBuilder(type)
        consumer.accept(builder)
        order.addTrade(builder.trade)
    }

    class TradeBuilder(private val type: Type) {
        private var quantity: Int = 0
        private var price: Double = 0.0
        private lateinit var stock: Stock

        lateinit var trade: Trade
            private set

        fun quantity(quantity: Int) {
           this.quantity = quantity
        }

        fun price(price: Double) {
            this.price = price
        }

        fun stock(consumer: Consumer<StockBuilder>) {
            val builder = StockBuilder()
            consumer.accept(builder)
            this.stock = builder.stock
            this.trade = Trade(type, stock, quantity, price)
        }
    }

    class StockBuilder {
        private lateinit var symbol: String

        lateinit var stock: Stock
            private set

        fun symbol(symbol: String) {
            this.symbol = symbol
        }

        fun market(market: String) {
            this.stock = Stock(symbol, market)
        }
    }
}