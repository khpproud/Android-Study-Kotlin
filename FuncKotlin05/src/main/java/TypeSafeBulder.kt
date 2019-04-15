package chap05

// 타입 안전 빌더
// with 예
val joinWith = with(listOf("1", "2", "3")) {
    joinToString(separator = "|")
}

// with 원형
inline fun <T, R> with(receiver: T, block: T.() -> R) : R {
    return receiver.block()
}

// apply 예
val html = buildString {
    append("<html>\n")
    append("\t<body>\n")
    append("\t\t<ul>\n")
    listOf(1, 2, 3).forEach {
        i -> append("\t\t\t<li>$i</li>\n")
    }
    append("\t\t<ul>\n")
    append("\t</body>\n")
    append("</html>")
}

// apply 원형
inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

fun main() {
    println(joinWith)
    println(html)
}