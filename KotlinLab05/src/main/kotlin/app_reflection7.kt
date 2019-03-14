package eighteen_one_seven

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

// 프로퍼티 레퍼런스 분석
val myVal: Int = 3
var myVar: Int = 5

class MyClass {
    val objVal: Int = 10
    var objVar: Int = 20
}

fun reflectionProperty(obj: Any?, arg: KProperty<*>) {
    println("property name : ${arg.name}, property type : ${arg.returnType}")
    if (obj != null) {
        println(arg.getter.call(obj))
    } else {
        println(arg.getter.call())
    }
}

fun reflectionMutableProperty(obj: Any?, arg: KMutableProperty<*>) {
    println("property name : ${arg.name}, property type : ${arg.returnType}")
    if (obj != null) {
        arg.setter.call(obj, 40)
        println(arg.getter.call(obj))
    } else {
        arg.setter.call(20)
        println(arg.getter.call())
    }
}

fun main() {
    reflectionProperty(null, ::myVal)
    reflectionMutableProperty(null, ::myVar)

    val obj: MyClass = MyClass()
    reflectionProperty(obj, MyClass::objVal)
    reflectionMutableProperty(obj, MyClass::objVar)
}