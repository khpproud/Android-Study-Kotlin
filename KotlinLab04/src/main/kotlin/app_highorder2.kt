package thirteen_one_two

// 람다 함수를 이용하여 고차함수 호출
fun hoFun(argFun: (x: Int) -> Int) {
    println("${argFun(10)}")
}

// 함수 참조를 이용한 고차 함수 호출
fun nameFun(x: Int): Int {
    return x * 5
}

// 익명 함수
val anonyFun1 = fun(x: Int): Int = x * 10

val anonyFun2 = fun(x: Int): Int {
    println("i am anonymous function")
    return x * 10
}

// 익명 함수 이용
fun hoFun2(argFun: (x: Int) -> Int) {
    println("${argFun(10)}")
}

fun main() {
    hoFun { it * 5 }
    hoFun(::nameFun)

    hoFun2(fun(x : Int): Int = x * 10)
}