package five.crossinline

import five.inline.boo

// crossinline 한정자 사용
fun boo(f: () -> Unit) { }

inline fun foo(crossinline f: () -> Unit) {
    boo { println("A"); f() }
}

fun main() {
    foo { println("B") }
}
