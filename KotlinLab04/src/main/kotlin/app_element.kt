package fourteen_four

fun main() {
    // contains()
    val result = listOf(2, 5, 3, 8).contains(7)
    println("contains result : $result")

    // elementAt()
    val result2 = listOf(2, 5, 10, 8).elementAt(2)
    println("elementAt test : $result2")

    // elementAtOrElse()
    val result3 = listOf(2, 5, 7, 10).elementAtOrElse(5) { 0 }
    println("elementAtOrElse test : $result3")

    // elementAtOrNull()
    val result4 = listOf(2, 4, 6, 3).elementAtOrNull(5)
    println("elementAtOrNull test : $result4")

    // first(), firstOrNull()
    //val result5 = listOf(2, 5, 10, 8).first { it % 3 == 0 }
    val result5 = listOf(2, 5, 10, 8).firstOrNull { it % 3 == 0}
    println("firstOrNull test : $result5")

    // last(), lastOrNull()
    val result6 = listOf(2, 5, 10, 8).last { it % 10 == 0 }
    println("last test : $result6")

    val result7 = listOf(2, 5, 10, 8).lastOrNull { it % 3 == 0 }
    println("lastOrNull test : $result7")

    // indexOf()
    val result8 = listOf(2, 5, 10, 2).indexOf(5)
    println("indexOf test : $result8")

    // indexOfFirst()
    val result9 = listOf(2, 5, 10, 2).indexOfFirst { it % 10 == 0 }
    println("indexOfFirst test : $result9")

    // indexOfLast()
    val result10 = listOf(2, 5, 10, 4).indexOfLast { it % 2 == 0 }
    println("indexOfLast test : $result10")
}