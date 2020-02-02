package chap03

import java.util.function.DoubleUnaryOperator

object Integration {
    @JvmStatic
    fun main(args: Array<String>) {
        val f: DoubleUnaryOperator = DoubleUnaryOperator { x -> x + 10 }
        println(integrate(f, 3.0, 7.0))
    }

    fun integrate(f: DoubleUnaryOperator, a: Double, b: Double): Double {
        return (f.applyAsDouble(a) + f.applyAsDouble(b)) * (b - a) / 2.0
    }
}