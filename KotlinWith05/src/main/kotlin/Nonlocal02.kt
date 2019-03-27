package five.nonlocal

// 람다식의 라벨 반환
inline fun <T> forEach(list: List<T>, body: (T) -> Unit) {
    for (i in list) body(i)
}

fun printMessageButNotError(messages: List<String>) {
    forEach(messages) messageProcessor@ {
        if (it == "ERROR") return@messageProcessor
        println(it)
    }
}

fun processMessageButNotError(conversations: List<List<String>>) {
    forEach(conversations) conv@{
        messages ->
        forEach(messages) {
            if (it == "ERROR") return@conv
            println(it)
        }
    }
}

fun main() {
    val list = listOf("A", "ERROR", "B", "ERROR", "C")
    printMessageButNotError(list)

    val conversations = listOf(
        listOf("A", "ERROR", "B"),
        listOf("ERROR", "C"),
        listOf("D")
    )
    processMessageButNotError(conversations)
}