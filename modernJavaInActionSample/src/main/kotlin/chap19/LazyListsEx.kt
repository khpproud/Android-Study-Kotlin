package chap19

import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val l: MyList<Int> = MyLinkedList(5, MyLinkedList(10, Empty()))

    println(l.head())

    val numbers: LazyList<Int> = from(2)
    val two = numbers.head()
    val three = numbers.tail().head()
    val four = numbers.tail().tail().head()
    println("$two $three $four")

    val numbers2 = from(2)
    val primeTwo = primes(numbers2).head()
    val primeThree = primes(numbers2).tail().head()
    val primeFour = primes(numbers2).tail().tail().head()
    println("$primeTwo $primeThree $primeFour")

    printAll(primes(from(2)))
}

interface MyList<T> {
    fun head(): T

    fun tail(): MyList<T>

    fun isEmpty() = true

    fun filter(p: (T) -> Boolean): MyList<T>
}

class MyLinkedList<T>(private val head: T,
                      private val tail: MyList<T>) : MyList<T> {
    override fun head(): T {
        return head
    }

    override fun tail(): MyList<T> {
        return tail
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun filter(p: (T) -> Boolean): MyList<T> {
        return when {
            isEmpty() -> this
            p.invoke(head) -> MyLinkedList(head, tail.filter(p))
            else -> tail.filter(p)
        }
    }
}

class Empty<T> : MyList<T> {
    override fun head(): T {
        throw UnsupportedOperationException()
    }

    override fun tail(): MyList<T> {
        throw UnsupportedOperationException()
    }

    override fun filter(p: (T) -> Boolean): MyList<T> {
        return this
    }
}

class LazyList<T>(private val head: T,
                  private val tail: () -> MyList<T>) : MyList<T> {
    override fun head(): T {
        return head
    }

    override fun tail(): MyList<T> {
        return tail.invoke()
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun filter(p: (T) -> Boolean): MyList<T> {
        return when {
            isEmpty() -> this
            p(head) -> LazyList(head) { tail().filter(p) }
            else -> tail().filter(p)
        }
    }
}

private fun from(n: Int): LazyList<Int> {
    return LazyList(n) { from(n + 1) }
}

private fun primes(numbers: MyList<Int>): MyList<Int> {
    return LazyList(numbers.head()) { primes(numbers.tail().filter { n -> n % numbers.head() != 0 }) }
}

private tailrec fun <T> printAll(numbers: MyList<T>) {
    if (numbers.isEmpty()) {
        return
    }

    println(numbers.head())
    printAll(numbers.tail())
}
