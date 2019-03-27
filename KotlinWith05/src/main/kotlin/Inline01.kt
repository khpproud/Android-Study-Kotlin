package five.inline

// inline 사용 예
inline fun printExecutionTime(f: () -> Unit) {
    val startTime = System.currentTimeMillis()
    f()
    val endTime = System.currentTimeMillis()
    println("It took ${endTime - startTime} milliseconds.")
}

fun measureOperation() {
    printExecutionTime {
        longOperation()
    }
}

fun longOperation() = Thread.sleep(1000L)

// noinline 한정자
fun boo(f: () -> Unit) { }

inline fun foo(before: () -> Unit, noinline f: () -> Unit) {
    before()
    boo(f)
}

fun main() {
    measureOperation()
}