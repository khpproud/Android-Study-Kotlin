package ch02_val_type_op

fun main() {
    val code: Int = 65
    val chFromCode: Char = code.toChar()
    println(chFromCode)

    val ch = '\uD55C'               // UniCode '한'
    println(ch)
}