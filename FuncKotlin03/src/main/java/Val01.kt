package chap03.immutable

object MutableVal {
    var count = 0
    val myString = "Mutable"
    get() {
        return "$field ${++count}"
    }
}

fun main() {
    println("1st call ${MutableVal.myString}")
    println("2nd call ${MutableVal.myString}")
    println("3rd call ${MutableVal.myString}")
}