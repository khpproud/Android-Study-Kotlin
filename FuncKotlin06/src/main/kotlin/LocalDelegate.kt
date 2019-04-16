package chap06

// 로컬 델리게이트 예
// shouldPrint 값 여부와 상관없이 localDelegate는 초기회되고 메모리 공간 차지
fun useDelegate(shouldPrint: Boolean) {
    val localDelegate = "델리게이트 사용됨"
    if (shouldPrint)
        println(localDelegate)
    println("종료됨")
}

// lazy 사용하여 shouldPrint 값이 참일 때만 사용됨
fun useDelegate1(shouldPrint: Boolean) {
    val localDelegate by lazy {
        "델리게이트 사용됨"
    }
    if (shouldPrint)
        println(localDelegate)
    println("종료됨")
}

fun main() {
    useDelegate(true)
    useDelegate1(true)
}