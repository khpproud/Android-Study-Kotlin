package eighteen_one_one

import kotlin.reflect.KClass

// 코틀린 클래스 레퍼런스 프로퍼티 선언
val myVal: KClass<*> = String::class

fun myFun(arg: KClass<*>) { }

// 클래스 타입
//val myVal2: KClass<String> = Double::class    // 에러 String 클래스의 레퍼런스만 대입 가능

// 자바 클래스 레퍼런스
val myVal3: Class<*> = String::class.java

// 클래스 정보 분석
open class MyClass

fun someFun(arg: KClass<*>) {
    println("class info .....")
    println("isAbstract : ${arg.isAbstract}")
    println("isCompanion : ${arg.isCompanion}")
    println("isData : ${arg.isData}")
    println("isFinal : ${arg.isFinal}")
    println("isInner : ${arg.isInner}")
    println("isOpen : ${arg.isOpen}")
    println("isSealed : ${arg.isSealed}")
}

fun main() {
    someFun(MyClass::class)
}