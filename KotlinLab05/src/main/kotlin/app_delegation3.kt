package nineteen_one_three

import kotlin.reflect.KProperty

// 프로퍼티의 위임 구현 예
class MySumDelegate {
    var result: Int = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        println("getValue call... ref : $thisRef, property : '${property.name}'")
        return result
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        result = 0
        println("setValue call... ref : $thisRef, value : $value, property : '${property.name}'")
        for (i in 1.. value) {
            result += i
        }
    }
}

class Test {
    var sum: Int by MySumDelegate()
}

fun main() {
    val obj: Test = Test()
    obj.sum = 10
    println(obj.sum)
    obj.sum = 5
    println(obj.sum)
}