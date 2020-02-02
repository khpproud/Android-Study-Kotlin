package chap09

import java.util.function.Consumer

object OnlineBankingMain {
    @JvmStatic
    fun main(args: Array<String>) {
        OnlineBankingImpl().processCustomer(1234)

        processCustomer(1234, Consumer { println("Hello") })
    }

    abstract class OnlineBanking {
        fun processCustomer(id: Int) {
            val customer = Database.getCustomerWithId(id)
        }

        abstract fun makeCustomerHappy(c: Customer)
    }

    class Customer

    private object Database {
        fun getCustomerWithId(id: Int) = Customer()
    }

    class OnlineBankingImpl : OnlineBanking() {
        override fun makeCustomerHappy(c: Customer) {
            println("Hello: $c")
        }
    }

    // With Lambda
    fun processCustomer(id: Int, makeCustomerHappy: Consumer<Customer>) {
        val customer = Database.getCustomerWithId(id)
        makeCustomerHappy.accept(customer)
    }
}