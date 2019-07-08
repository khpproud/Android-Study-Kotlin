package ch08_generic

// 배열의 인덱스 찾아내기
fun <T> find(a: Array<T>, target: T): Int {
    for (i in a.indices) {
        if (a[i] == target) return i
    }
    return -1
}

fun main() {
    val arr1: Array<String> = arrayOf("Apple", "Banana", "Cherry", "Durian")
    val arr2: Array<Int> = arrayOf(1, 2, 3, 4)

    println("arr.indices ${arr1.indices}")              // 배열의 유효범위 반환
    println(find<String>(arr1, "Cherry"))
    println(find(arr2, 2))
}