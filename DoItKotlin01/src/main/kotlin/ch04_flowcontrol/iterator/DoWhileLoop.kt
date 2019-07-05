package ch04_flowcontrol.iterator

// do - while 문 사용 예
fun main() {
    do {
        print("Enter the number : ")
        val input = readLine()!!.toInt()

        for (i in 0 until input) {
            for (j in 0 until input) {
                print((i + j) % input + 1)
            }
            println()
        }
    } while (input != 0)
}