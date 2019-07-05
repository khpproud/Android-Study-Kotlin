package ch04_flowcontrol.iterator

// while 문으로 팩토리알 표현
fun main() {
    print("Enter the number : ")
    var number = readLine()!!.toInt()
    var factorial: Long = 1

    while (number > 0) {
        factorial *= number
        number--
    }

    println("Factorial : $factorial")
}