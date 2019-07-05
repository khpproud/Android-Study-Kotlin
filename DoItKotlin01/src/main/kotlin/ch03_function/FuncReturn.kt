package ch03_function

// 반환값에 일반 함수 사용
fun main() {
    println("funcReturn : ${funcReturn()}")
}

fun funcReturn(): Int = sum(2, 2)