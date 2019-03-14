package eighteen_one_five

import kotlin.reflect.KFunction

// 함수 레퍼런스 정보 추출
fun myFun(no: Int, name: String): Boolean {
    return true
}

fun reflectionFun(argFun: KFunction<*>) {
    print(argFun.name)

    val parameters = argFun.parameters
    print("(")
    for (parameter in parameters) {
        print("${parameter.name}: ${parameter.type}")
        if (parameter.index < parameters.size - 1)
            print(", ")
    }
    print("): ")
    println(argFun.returnType)
}

fun main() {
    reflectionFun(::myFun)
}