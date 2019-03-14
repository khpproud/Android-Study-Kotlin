package fifteen_two

import java.lang.ClassCastException
import java.lang.Exception

// 표현식으로 try-catch 이용
fun some(arg: String): Int {
    return try {
        println("try... top")
        arg.toInt()
    } catch (e: ClassCastException) {
        println(e.toString())
        -1
    } catch (e: Exception) {
        println(e.toString())
        -2
    } finally {
        println("finally...")
        100
    }
}

// 예외 발생시키기 throw
fun some1(arg: Int): Int {
    if (arg < 1)
        throw Exception("Parameter must be greater than zero!!!")
    else {
        var sum = 0
        for (i in 1 .. arg) {
            sum += i
        }
        return sum
    }
}

// Exception 클래스 정의
class MyException(msg: String): Exception(msg) {
    val errorData: String = "some error data"
    fun errorFun() {
        println("errorFun call...")
    }
}

fun some2() {
    throw MyException("My error...")
}

// 표현식으로서 throw
fun some3() {
    val name: String? = null
    val s: String = name ?: throw IllegalArgumentException("Name required...")
    println("some1 bottom...")
}

fun some4(arg: Int): Nothing {
    if (arg > 0)
        throw Exception("some4 exception... arg > 0 true")
    else
        throw Exception("some4 exception... arg > 0 false")
}

fun main() {
    println("${some("10")}")

    println("${some("a")}")

    try {
        println(some1(5))

        println(some1(-2))

        println("main bottom...")
    } catch (e: Exception) {
        println("Exception... $e")
    }

    try {
        some2()
    } catch (e: MyException) {
        println("error message : $e")
        println("error data : ${e.errorData}")
        e.errorFun()
    }

    try {
        some3()
    } catch (e: Exception) {
        println(e)
    }

    try {
        some4(10)
    } catch (e: Exception) {
        println(e)
    }
}

