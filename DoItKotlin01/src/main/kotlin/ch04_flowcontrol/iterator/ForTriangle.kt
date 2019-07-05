package ch04_flowcontrol.iterator

// 반복문을 이용해 삼각형 출력하기
fun main() {
    print("Enter the lines : ")
    val n = readLine()!!.toInt()

    for (line in 1.. n) {
        for (space in 1.. (n - line)) print(" ")        // 공백 출력
        for (star in 1 until 2 * line) print("*")
        println()
    }
}