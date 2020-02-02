package chap10.dsl

import chap10.dsl.model.Order
import chap10.dsl.model.Stock
import chap10.dsl.model.Trade
import chap10.dsl.model.Type

class MethodChainingOrderBuilder private constructor(private val order: Order) {

    companion object {

        fun forCustomer(customer: String): MethodChainingOrderBuilder {
            return MethodChainingOrderBuilder(
                Order(
                    customer
                )
            )
        }
    }

    fun end() = order

    fun buy(quantity: Int): TradeBuilder =
        TradeBuilder(
            this,
            Type.BUY,
            quantity
        )

    fun sell(quantity: Int): TradeBuilder =
        TradeBuilder(
            this,
            Type.SELL,
            quantity
        )

    private fun addTrade(trade: Trade): MethodChainingOrderBuilder {
        order.addTrade(trade)
        return this
    }

    class TradeBuilder(private val builder: MethodChainingOrderBuilder,
                       private val type: Type,
                       private val quantity: Int) {

        fun stock(symbol: String): StockBuilder =
            StockBuilder(builder, type, symbol, quantity)
    }

    class StockBuilder(private val builder: MethodChainingOrderBuilder,
                       private val type: Type,
                       private val symbol: String,
                       private val quantity: Int) {
        fun on(market: String): TradeBuilderWithStock {
            val stock = Stock(symbol, market)
            return TradeBuilderWithStock(
                builder,
                type,
                stock,
                quantity
            )
        }
    }

    class TradeBuilderWithStock (private val builder: MethodChainingOrderBuilder,
                                 private val type: Type,
                                 private val stock: Stock,
                                 private val quantity: Int) {
        fun at(price: Double): MethodChainingOrderBuilder {
            val trade: Trade =
                Trade(type, stock, quantity, price)
            return builder.addTrade(trade)
        }
    }
}