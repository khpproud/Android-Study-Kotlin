package chap01

fun main() {
    println("Input your first name")
    val firstName = readLine()

    println("Input your last name")
    val lastName = readLine()

    println("Hi $firstName $lastName, let us have a quick math test. Enter two numbers separated by space.")
    val (a, b) = readLine()!!.split(' ').map(String::toInt)

    println("What is $a + $b ?")
    println("Your answer is ${if (readLine()!!.toInt() == (a + b)) "correct" else "incorrect"}")
}