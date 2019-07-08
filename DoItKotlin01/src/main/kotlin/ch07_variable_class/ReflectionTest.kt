package ch07_variable_class

import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

// 리플렉션 테스트
class User(val id: Int, val name: String, var grade: String = "Normal") {
    fun check() {
        if (grade == "Normal")
            println("You need to get the silver grade")
    }
}

fun main() {
    println(User::class)                        // 타입을 출력
    val classInfo = User::class
    classInfo.memberProperties.forEach {
        println("Property name : ${it.name}, type : ${it.returnType}")
    }
    classInfo.memberFunctions.forEach {
        println("Function name : ${it.name}, type : ${it.returnType}")
    }
    getKotlinType(classInfo)                    // 함수에 전달해서 자료형을 알아냄
}

fun getKotlinType(obj: KClass<*>) {
    println(obj.qualifiedName)
}