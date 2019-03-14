package eighteen_one_two

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

open class MyClass(no: Int) {
    constructor(no: Int, name: String): this(no){ }
    constructor(no: Int, name: String, email: String): this(no) { }
}

fun someFun(arg: KClass<*>) {
    val constructors = arg.constructors
    for (constructor in constructors) {
        print("constructor... ")
        val parameters = constructor.parameters
        for (parameter in parameters) {
            print("${parameter.name} : ${parameter.type} ...")
        }
        println()
    }

    print("primary constructor... ")
    val primaryConstructor = arg.primaryConstructor
    primaryConstructor?.let {
        val parameters = it.parameters
        for (parameter in parameters) {
            print("${parameter.name} : ${parameter.type} ... ")
        }
        println()
    }
}

fun main() {
    someFun(MyClass::class)
}