package ch03_function

// 매개변수 개수에 따라 함다식을 구성하는 예
fun main() {
    // 매개변수 없는 함다식
    noParam({"Hello Kotlin"})
    noParam { "Hello Kotlin" }

    // 매개변수가 1개 있는 람다식
    oneParam({ a -> "Hello Kotlin! $a" })
    oneParam { "Hello Kotlin $it" }

    // 매개변수가 2개 있는 람다식
    moreParam { a, b -> "Hello Kotlin! $a $b"}

    // 인자와 함께 람다식을 사용하는 경우
    withArgs("Arg1", "Arg2", { a, b -> "Hello Kotlin $a $b"} )
    // 마지막 인자가 람다식인 경우 소괄호 바깥으로 분리 가능
    withArgs("Arg1", "Arg2") {a, b -> "Hello Kotlin $a $b"}

    // 2개의 람다식을 매개변수로 사용
    twoLambda({ a, b -> "First $a $b"}, { "Second $it" })
    // 마지막 람다식은 소괄호 밖으로 분리 가능
    twoLambda({ a, b -> "First $a $b"}) { "Second $it"}
}

fun noParam(out: () -> String) = println(out())

fun oneParam(out: (String) -> String) = println(out("One Param"))

fun moreParam(out: (String, String) -> String) = println(out("One", "Two"))

fun withArgs(a: String, b: String, out: (String, String) -> String) {
    println(out(a, b))
}

fun twoLambda(first: (String, String) -> String, second: (String) -> String) {
    println(first("One", "Two"))
    println(second("Three"))
}