package chap19

fun main() {
    println(repeat(3) { x:Int -> 2 * x }.invoke(10))
}

fun <A, B, C> compose(g: (B) -> C, f: (A) -> B): (A) -> C {
    return { x -> g.invoke(f.invoke(x)) }
}

fun <A> repeat(n: Int, f: (A) -> A): (A) -> A {
    return if (n == 0) { x -> x }
        else compose(f, repeat(n - 1, f))
}