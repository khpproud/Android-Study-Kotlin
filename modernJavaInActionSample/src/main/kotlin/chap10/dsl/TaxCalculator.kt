package chap10.dsl

import chap10.dsl.MixedBuilder.Companion.buy
import chap10.dsl.MixedBuilder.Companion.forCustomer
import chap10.dsl.MixedBuilder.Companion.sell
import chap10.dsl.TaxCalculator.Companion.calculate
import chap10.dsl.model.Order
import chap10.dsl.model.Tax

class TaxCalculator {

    companion object {
        fun calculate(order: Order, useRegional: Boolean, useGeneral: Boolean, useSurcharge: Boolean): Double {
            var value = order.getValue()
            if (useRegional) {
                value = Tax.regional(value)
            }
            if (useGeneral) {
                value = Tax.general(value)
            }
            if (useSurcharge) {
                value = Tax.surcharge(value)
            }
            return value
        }
    }

    private var useRegional: Boolean = false
    private var useGeneral: Boolean = false
    private var useSurcharge: Boolean = false

    fun withTaxRegional(): TaxCalculator {
        useRegional = true
        return this
    }


    fun withTaxGeneral(): TaxCalculator {
        useGeneral = true
        return this
    }

    fun withTaxSurcharge(): TaxCalculator {
        useSurcharge = true
        return this
    }

    fun calculate(order: Order): Double {
        return calculate(order, useRegional, useGeneral, useSurcharge)
    }

    var taxFunction: (Double) -> Double = { d -> d }

    fun with(f: (Double) -> Double): TaxCalculator {
        // Like UnaryOperator.andThen
        taxFunction = taxFunction.andThen(f)
        return this
    }

    fun calculateF(order: Order): Double {
        return taxFunction.invoke(order.getValue())
    }
}

private fun <T> ((T) -> T).andThen(f: (T) -> T): (T) -> T {
    return { t: T -> f.invoke(this.invoke(t)) }
}

fun main() {
    val order = forCustomer("BigBank",
        buy { t -> t.quantity(80)
            .stock("IBM")
            .on("NYSE")
            .at(125.0)},
        sell { t -> t.quantity(50)
            .stock("GOOGLE")
            .on("NASDAQ")
            .at(375.0)})

    val value = calculate(order, useRegional = true, useGeneral = false, useSurcharge = true)
    println("Boolean arguments: ${String.format("%.2f", value)}")

    val value2 = TaxCalculator().withTaxRegional()
        .withTaxSurcharge()
        .calculate(order)
    println("Method chaining: ${String.format("%.2f", value2)}")

    val value3 = TaxCalculator().with(Tax::regional)
        .with(Tax::surcharge)
        .calculateF(order)

    println("Method references: ${String.format("%.2f", value3)}")
}