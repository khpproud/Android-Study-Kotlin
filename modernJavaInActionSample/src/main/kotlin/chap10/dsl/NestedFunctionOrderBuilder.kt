package chap10.dsl

import chap10.dsl.model.Order
import chap10.dsl.model.Stock
import chap10.dsl.model.Trade
import chap10.dsl.model.Type

class NestedFunctionOrderBuilder {

    companion object {
        fun order(customer: String, vararg trades: Trade): Order {
            val order = Order(customer)
            trades.forEach { trade -> order.addTrade(trade) }
            return order
        }

        fun buy(quantity: Int, stock: Stock, price: Double): Trade {
            return buildTrade(quantity, stock, price, Type.BUY)
        }

        fun sell(quantity: Int, stock: Stock, price: Double): Trade {
            return buildTrade(quantity, stock, price, Type.SELL)
        }

        fun buildTrade(quantity: Int, stock: Stock, price: Double, type: Type): Trade {
            return Trade(type, stock, quantity, price)
        }

        fun at(price: Double): Double = price

        fun stock(symbol: String, market: String): Stock {
            return Stock(symbol, market)
        }

        fun on(market: String): String = market
    }
}