package five.nonlocal

// 논로컬 반환 예
fun forEach(list: List<Int>, body: (Int) -> Unit) {
    for (i in list)
        body(i)
}

fun maxBounded(list: List<Int>, upperBound: Int, lowerBound: Int): Int {
    var currentMax = lowerBound
    for (i in list) {
        when {
            i > upperBound -> return upperBound
            i > currentMax -> currentMax = i
        }
    }
    return currentMax
}

inline fun forEach2(list: List<Int>, body: (Int) -> Unit) {
    for (i in list)
         body(i)
}

// 인라인 함수의 라다식 안에 사용된 return을 논로컬반환이라 함
fun maxBounded2(list: List<Int>, upperBound: Int, lowerBound: Int): Int {
    var currentMax = lowerBound
    forEach2(list) {
        i ->
        when {
            i > upperBound -> return upperBound
            i > currentMax -> currentMax = i
        }
    }
    return currentMax
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    forEach(list) { println(it) }
}