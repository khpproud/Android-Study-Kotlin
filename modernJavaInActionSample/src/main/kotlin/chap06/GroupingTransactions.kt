package chap06

import java.util.*
import java.util.stream.Collectors.groupingBy

object GroupingTransactions {
    val transactions = listOf(
        Transaction(Currency.EUR, 1500.0),
        Transaction(Currency.USD, 2300.0),
        Transaction(Currency.GBP, 9900.0),
        Transaction(Currency.EUR, 1100.0),
        Transaction(Currency.JPY, 7800.0),
        Transaction(Currency.CHF, 6700.0),
        Transaction(Currency.EUR, 5600.0),
        Transaction(Currency.USD, 4500.0),
        Transaction(Currency.CHF, 3400.0),
        Transaction(Currency.GBP, 3200.0),
        Transaction(Currency.USD, 4600.0),
        Transaction(Currency.JPY, 5700.0),
        Transaction(Currency.EUR, 6800.0)
    )

    @JvmStatic
    fun main(args: Array<String>) {
        groupImperatively()
        groupFunctionally()
    }

    private fun groupImperatively() {
        val transactionsByCurrencies: MutableMap<Currency, MutableList<Transaction>> = mutableMapOf()
        for (transaction in transactions) {
            val currency = transaction.currency
            var transactionsForCurrency = transactionsByCurrencies[currency]
            if (transactionsForCurrency == null) {  // not added yet
                transactionsForCurrency = mutableListOf()
                transactionsByCurrencies[currency] = transactionsForCurrency    // add list
            }
            transactionsForCurrency.add(transaction)
        }

        println(transactionsByCurrencies)
    }

    private fun groupFunctionally() {
        val transactionsByCurrencies = transactions.stream()
            .collect(groupingBy(Transaction::currency))
        println(transactionsByCurrencies)
    }
}

data class Transaction(val currency: Currency,
                       val value: Double) {
    override fun toString() = "$currency: $value"
}

enum class Currency {
    EUR, USD, JPY, GBP, CHF
}