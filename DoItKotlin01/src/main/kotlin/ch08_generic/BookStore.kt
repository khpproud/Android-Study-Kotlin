package ch08_generic

import java.util.*

// 서점 예제
fun main() {
    // books
    val bookName = arrayOf("java", "c", "c++", "kotlin", "c#", "html")
    val price = 200.0f
    val studentDiscount = .25f
    val teacherDiscount = .15f

    // `in` 으로 감싼 이유는 in이 예약어 이기 때문
    val scanner = Scanner(System.`in`)

    println("** Welcome to Book Store!! **")
    do {
        println(Arrays.toString(bookName))
        println("Which book do you want?")
        print("Answer : ")
        val book = scanner.nextLine()

        if (bookName.contains(book.toLowerCase())) {
            println("The choice book is $book. Its price is \$200")
            println("teacher and student by discount... \nWhat's your job?(student, teacher, etc)")
            print("Answer : ")
            val occupation = scanner.nextLine()

            when (occupation.toLowerCase()) {
                "student" -> calculatePrice(price, studentDiscount)
                "teacher" -> calculatePrice(price, teacherDiscount)
                else -> println("No adapt Discount.\nThe price is \$200.")
            }
        } else if (book == "exit" || book == "q") {
            break
        } else {
            println("Sorry~ Not inventory $book")
        }
    } while (true)
}

private fun calculatePrice(orig: Float, x: Float): Unit {
    val result = orig - (orig * x)
    println("Total price is \$$result.")
}