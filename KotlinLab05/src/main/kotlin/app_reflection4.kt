package eighteen_one_four

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions

// 함수 정보 분석
open class SuperClass {
    fun superFun() { }
}

class MyClass: SuperClass() {
    fun myFun() { }
    fun String.someFun() { }
}

fun someFun(arg: KClass<*>) {
    // 확장 함수를 제외한 클래스에 선언된 모든 함수 반환
    println("declaredMemberFunctions...")
    val functions = arg.declaredMemberFunctions
    for (function in functions) {
        println("${function.name} : ${function.returnType}")
    }

    // 확장 함수를 제외한 클래스와 상위 클래스에 선언된 모든 함수 반환
    println("memberFunctions...")
    val functions2 = arg.memberFunctions
    for (function in functions2) {
        println("${function.name} : ${function.returnType}")
    }

    // 클래스에 선언된 확장 함수 반환
    println("declaredMemberExtensionFunctions...")
    val functions3 = arg.declaredMemberExtensionFunctions
    for (function in functions3) {
        println("${function.name} : ${function.returnType}")
    }
}

fun main() {
    someFun(MyClass::class)
}