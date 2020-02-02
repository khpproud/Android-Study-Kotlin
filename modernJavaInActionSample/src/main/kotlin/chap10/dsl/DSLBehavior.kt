package chap10.dsl

import chap10.dsl.LambdaOrderBuilder.Companion.order
import chap10.dsl.model.*
import chap10.dsl.MethodChainingOrderBuilder.Companion.forCustomer
import chap10.dsl.MixedBuilder.Companion.buy
import chap10.dsl.MixedBuilder.Companion.sell
import chap10.dsl.MixedBuilder.Companion.forCustomer as forCustomer2
import chap10.dsl.NestedFunctionOrderBuilder.Companion.at
import chap10.dsl.NestedFunctionOrderBuilder.Companion.buy
import chap10.dsl.NestedFunctionOrderBuilder.Companion.on
import chap10.dsl.NestedFunctionOrderBuilder.Companion.order
import chap10.dsl.NestedFunctionOrderBuilder.Companion.sell
import chap10.dsl.NestedFunctionOrderBuilder.Companion.stock
import java.util.function.Consumer

object DSLBehavior {
    @JvmStatic
    fun main(args: Array<String>) {
        plain()
        methodChaining()
        nestedFunction()
        lambda()
        mixed()
    }

    private fun plain() {
        val order = Order("BigBank")

        val stock1 = Stock("IBM", "NYSE")

        val trade1 = Trade(Type.BUY, stock1, 80, 125.0)
        order.addTrade(trade1)

        val stock2 = Stock("GOOGLE", "NASDAQ")

        val trade2 = Trade(Type.BUY, stock2, 50, 375.0)
        order.addTrade(trade2)

        println("Plain: $order")
    }

    private fun methodChaining() {
        val order: Order = forCustomer("BigBank")
            .buy(80)
            .stock("IBM")
            .on("NYSE")
            .at(125.0)
            .sell(50)
            .stock("GOOGLE")
            .on("NASDAQ")
            .at(375.0)
            .end()

        println("Method chaining: $order")
    }

    private fun nestedFunction() {
        val order = order(
            "BigBank",
            buy(
                80,
                stock("IBM", on("NYSE")),
                at(125.0)
            ),
            sell(
                50,
                stock("GOOGLE", on("NASDAQ")),
                at(375.0)
            )
        )

        println("Nested Function: $order")
    }

    private fun lambda() {
        val order = order(Consumer { o ->
            o.forCustomer("BigBank")
            o.buy(Consumer { t ->
                t.quantity(80)
                t.price(125.0)
                t.stock(Consumer { s ->
                    s.symbol("IBM")
                    s.market("NYSE")
                })
            })
            o.sell(Consumer { t ->
                t.quantity(50)
                t.price(375.0)
                t.stock(Consumer { s ->
                    s.symbol("GOOGLE")
                    s.market("NASDAQ")
                })
            })
        })

        println("Lambda: $order")
    }

    private fun mixed() {
        val order = forCustomer2("BigBank",
            buy { t ->
                t.quantity(80)
                    .stock("IBM")
                    .on("NYSE")
                    .at(125.0) },
            sell { t ->
                t.quantity(50)
                    .stock("GOOGLE")
                    .on("NASDAQ")
                    .at(375.0) })

        println("Mixed: $order")
    }

}