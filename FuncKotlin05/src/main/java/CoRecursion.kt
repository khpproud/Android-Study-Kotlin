package chap05

// 재귀 및 corecursion(값을 받아 복잡한 값을 생산) 예
// 일반적인 재귀는 기본 조건에 도달하기 위해 값을 줄임
// Fibonacci
fun tailrecFib(n: Long): Long {
    tailrec fun go(n: Long, prev: Long, cur: Long): Long {
        return if(n == 0L)
            prev
        else
            go(n - 1, cur, prev + cur)
    }
    return go(n, 0, 1)
}

// Factorial
fun tailrecFactorial(n: Long): Long {
    tailrec fun go(n: Long, acc: Long): Long {
        return if (n <= 0)
            acc
        else
            go(n - 1, n * acc)
    }
    return go(n, 1)
}

// FunList
sealed class FunList<out T> {
    object Nil: FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T>()

    fun forEach(f: (T) -> Unit) {
        tailrec fun go(list: FunList<T>, f: (T) -> Unit) {
            when (list) {
                is Cons -> {
                    f(list.head)
                    go(list.tail, f)
                }
                is Nil -> Unit
            }
        }
        go(this, f)
    }

    fun <R> fold(init: R, f: (R, T) -> R): R {
        tailrec fun go(list: FunList<T>, init: R, f: (R, T) -> R): R =
                when (list) {
                    is Cons -> go(list.tail, f(init, list.head), f)
                    is Nil -> init
                }
        return go(this, init, f)
    }

    fun reverse(): FunList<T> = fold(Nil as FunList<T>) { acc, i -> Cons(i, acc) }

    fun <R> foldRight(init: R, f: (R, T) -> R): R = this.reverse().fold(init, f)

    fun <R> map(f:(T) -> R): FunList<R> = foldRight(Nil as FunList<R>) {
        tail, head -> Cons(f(head), tail)
    }
}


// co-recursion 예 => 무한한 시퀀스를 만듬
fun <T, S> unfold(s: S, f: (S) -> Pair<T, S>?): Sequence<T> {
    val result = f(s)
    return if (result != null)
        sequenceOf(result.first) + unfold(result.second, f)
    else
        sequenceOf()
}

// unfold 를 사용하여 단일 요소를 여러번 반복하는 함수 생성
fun <T> elements(element: T, numOfValues: Int): Sequence<T> {
    return unfold(1) {i ->
        if (numOfValues > i)
            element to i + 1
        else
            null
    }
}

// unfold 를 이용한 Factorial
fun factorial(size: Int): Sequence<Long> {
    return sequenceOf(1L) + unfold(1L to 1) { (acc, n) ->
        if (size > n) {
            val x = n * acc
            (x) to (x to n + 1)
        } else
            null
    }
}

// unfold 를 이용한 Fibonacci
fun fib(size: Int): Sequence<Long> {
    return sequenceOf(1L) + unfold(Triple(0L, 1L, 1)) {
        (cur, next, n) ->
            if (size > n) {
                val x = cur + next
                (x) to Triple(next, x, n + 1)
            } else
                null
    }
}

fun main() {
    val strings = elements("Kotlin", 5)
    strings.forEach(::println)

    val factorialNums = factorial(10)
    factorialNums.forEach(::println)

    val fibNums = fib(10)
    fibNums.forEach(::println)
}