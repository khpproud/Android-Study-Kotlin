package eighteen_one_three

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

// 프로퍼티 정보 분석
open class SuperClass {
    val superVal: Int = 10
}

class MyClass(val no: Int): SuperClass() {
    val myVal: String = "Hello"
    var myVar: Boolean = true
    val String.somVal: String
    get() = "world"
}

fun someFun(arg: KClass<*>) {
    // 확장 프로퍼티를 제외한 클래스에 선언된 모든 프로퍼티 반환
    val properties = arg.declaredMemberProperties
    println("declaredMemberProperties...")
    for (property in properties) {
        println("${property.name} : ${property.returnType} ...")
    }

    // 확장 프롶티를 제외한 클래스와 상위 클래스에 정의된 모든 프로퍼티 반환
    val properties2 = arg.memberProperties
    println("memberProperties...")
    for (property in properties2) {
        println("${property.name} : ${property.returnType}")
    }

    // 클래스에 선언된 모든 확장 프로퍼티 반환
    val properties3 = arg.declaredMemberExtensionProperties
    println("declaredMemberExtensionProperties...")
    for (property in properties3) {
        println("${property.name} :${property.returnType}")
    }
}

fun main() {
    someFun(MyClass::class)
}