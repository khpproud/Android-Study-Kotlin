package chap06

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// 커스템 델리게이트 생성 및 사용 예

// 다음과 같이 사용
var myEven: Int by makeEven(0) {
    property, oldValue, newValue, wasEven ->
        println("${property.name} $oldValue -> $newValue, Even:$wasEven")
}

fun main() {
    myEven = 6
    println("myEven:$myEven")
    myEven = 3
    println("myEven:$myEven")
    myEven = 5
    println("myEven:$myEven")
    myEven = 8
    println("myEven:$myEven")
}

// var 속성에 대한 델리게이트를 만들려면 ReadWriteProperty 인터페이스를 구현해야 함
abstract class MakeEven(initialValue: Int): ReadWriteProperty<Any?, Int> {
    // 값 할당및 변환을 위해 임시로 저장할 멤버 변수
    private var value: Int = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>) = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        val wasEven = value % 2 == 0
        if (wasEven)
            this.value = value
        else
            this.value = value + 1
        afterAssignmentCall(property, value, value, wasEven)
    }

    abstract fun afterAssignmentCall(property: KProperty<*>, oldValue: Int, newValue: Int, wasEven: Boolean): Unit
}

// 델리게이트를 적용허기 위한 함수 정의
inline fun makeEven(initialValue: Int,
                    crossinline onAssignment:(property: KProperty<*>, oldValue: Int, newValue: Int,
                                              wasEven: Boolean) -> Unit): ReadWriteProperty<Any?, Int> = object :
        MakeEven(initialValue) {
    override fun afterAssignmentCall(property: KProperty<*>, oldValue: Int, newValue: Int, wasEven: Boolean) =
            onAssignment(property, oldValue, newValue, wasEven)
}