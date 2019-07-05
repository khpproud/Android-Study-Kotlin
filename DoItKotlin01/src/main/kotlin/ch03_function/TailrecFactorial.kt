package ch03_function

// 꼬리 재귀를 사용해 팩토리알 만들기
fun main() {
    val number = 5
    println("Factorial : $number -> ${factorialTail(number)}")
}

tailrec fun factorialTail(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorialTail(n - 1, run * n)
}