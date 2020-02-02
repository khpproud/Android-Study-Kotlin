package chap09

import java.util.function.Supplier

object FactoryMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val p1 = ProductFactory.createProduct("loan")
        println("p1: ${p1.javaClass.name}")

        val fundSupplier = Supplier(::Fund)
        val p2 = fundSupplier.get()
        println("p2: ${p2.javaClass.name}")

        val p3 = ProductFactory.createProductLambda("stock")
        println("p3: ${p3.javaClass.name}")
    }

    private object ProductFactory {
        fun createProduct(name: String): Product {
            return when (name.toLowerCase()) {
                "loan" -> Loan()
                "stock" -> Stock()
                "fund" -> Fund()
                else -> throw IllegalArgumentException("No such Product $name")
            }
        }

        fun createProductLambda(name: String): Product {
            val p = map[name]
            return p?.get() ?: throw IllegalArgumentException("No such product $name")
        }
    }

    interface Product
    class Loan : Product
    class Stock : Product
    class Fund : Product

    val map: HashMap<String, Supplier<out Product>> = hashMapOf(
        "loan" to Supplier(::Loan),
        "stock" to Supplier(::Stock),
        "fund" to Supplier(::Fund)
    )
}