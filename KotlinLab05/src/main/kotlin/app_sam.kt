package nineteen_two

import JavaInterface1
import SamClass

// 코틀린에서 object 클래스로 setter 함수 이용
fun main() {
    val obj: SamClass = SamClass()
    obj.setInterface(object: JavaInterface1 {
        override fun callback() {
            println("hello kotlin")
        }
    })
    obj.callback.callback()

    // 코틀린에서 람다 함수로 setter 함수 이용
    obj.setInterface { println("hello kotlin") }
    obj.callback.callback()
}