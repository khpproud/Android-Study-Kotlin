package chap04

// 속성으로서의 함수 예


fun main() {
    val sum = { x: Int, y: Int -> x + y}
    println("Sum ${sum(10, 13)}")
    println("Sum $sum")

    val reverse: (Int) -> Int
    reverse = { number ->
        var n = number
        var revNumber = 0
        while (n > 0) {
            val digit = n % 10
            revNumber = revNumber * 10 + digit
            n /= 10
        }
        revNumber
    }

    println("reverse 123 ${reverse(123)}")
    println("reverse 987 ${reverse(987)}")
}