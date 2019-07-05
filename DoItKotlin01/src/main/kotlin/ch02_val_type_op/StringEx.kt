package ch02_val_type_op

fun main() {
    var str1: String = "Hello"
    var str2 = "world"
    var str3 = "Hello"

    println("str1 === str2 : ${str1 === str2}")
    println("str1 === str3 : ${str1 === str3}")
    println("str1 == str3 : ${str1 == str3}")
}