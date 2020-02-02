package chap05

import java.util.Comparator
import java.util.Comparator.comparing
import java.util.stream.Collectors
import java.util.stream.Collectors.joining
import kotlin.math.max
import kotlin.math.min

object Practice {
    @JvmStatic
    fun main(args: Array<String>) {
        // 1. Find all transactions from year 2011 and sort them by value (small to high).
        transactions.stream()
            .filter { transaction -> transaction.year == 2011 }
            .sorted(comparing(Transaction::value))
            .forEach(::println)

        println("-----")
        // 2. What are all the unique cities where the traders work?
        transactions.stream()
            .map { transaction -> transaction.trader.city }
            .distinct()
            .forEach(::println)

        println("-----")
        // 3. Find all traders from Cambridge and sort them by name.
        transactions.stream()
            .filter { transaction -> transaction.trader.city == "Cambridge" }
            .map { transaction -> transaction.trader.name }
            .distinct()
            .sorted()
            .forEach(::println)

        println("------")
        // 4. Return a string of all traders’ names sorted alphabetically.
        transactions.stream()
            .map { transaction -> transaction.trader.name }
            .distinct()
            .sorted()
//            .collect(joining())
            .forEach(::println)

        println("-----")
        // 5. Are there any trader based in Milan?
        val isInMilan = transactions.stream()
            .anyMatch { transaction -> transaction.trader.city == "Milan" }
        println("Traders present in Milan: $isInMilan")

        println("-----")
        // 6. Update all transactions so that the traders from Milan are set to Cambridge.
        val sumOfValues = transactions.stream()
            .filter { transaction -> transaction.trader.city == "Cambridge" }
            .map(Transaction::value)
            .reduce(0, Int::plus)
        println("Sum values about Cambridge's trader: $sumOfValues")

        println("-----")
        // 7. What's the highest value in all the transactions?
        val maxOfValues = transactions.stream()
//            .map(Transaction::value)
//            .reduce(0, ::max)
//        println("Max transaction value: $maxOfValues")
            .max(comparing(Transaction::value))
            .ifPresent(::println)


        // 8. What's the smallest value in all the transactions?
        transactions.stream()
            .map(Transaction::value)
            .reduce(::min)
            .ifPresent { t -> println("Min transaction value: $t") }
    }
}

data class Trader(val name: String, val city: String) {
    override fun toString(): String = "Trader $name in $city."
}

data class Transaction(val trader: Trader, val year: Int, val value: Int)

val raoul = Trader("Raoul", "Cambridge")
val mario = Trader("Mario", "Milan")
val alan = Trader("Alan", "Cambridge")
val brian = Trader("Brian", "Cambridge")

val transactions: List<Transaction> = listOf(
    Transaction(brian, 2011, 300),
    Transaction(raoul, 2012, 1000),
    Transaction(raoul, 2011, 400),
    Transaction(mario, 2012, 710),
    Transaction(mario, 2012, 700),
    Transaction(alan, 2012, 950)
)