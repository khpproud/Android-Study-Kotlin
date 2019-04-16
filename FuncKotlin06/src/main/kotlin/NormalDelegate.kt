package chap06

import kotlin.properties.Delegates

// Delegates.notNull() 함수와 lateinit - var
var notNullStr: String by Delegates.notNull<String>()

lateinit var notInit: String

// lazy 함수 - val
val myLazyVal: String by lazy {
    println("방금 초기화 됨")
    "느긋한 val"
}

// Delegates.observable 을 사용한 속성 값 변경 관찰
var myStr: String by Delegates.observable("<초기 값>") {
    property, oldValue, newValue ->
    println("속성 `${property.name}` 을 `$oldValue` 에서 `$newValue`로 변경한다")
}

// Delegates.vetoable 을 사용한 할당 조건 설정
var myIntEven: Int by Delegates.vetoable(0) {
    property, oldValue, newValue ->
        println("${property.name} $oldValue -> $newValue")
        newValue % 2 == 0
}

// 증감 연산자도 조건에 만족하는지 확인할 수 있음
var myCounter:Int by Delegates.vetoable(0) {
    property, oldValue, newValue ->
        println("${property.name} $oldValue -> $newValue")
        newValue > oldValue
}

fun main() {
    notNullStr = "초기 값"
    println(notNullStr)

    notInit = "초기 값"
    println(notInit)

    println("아직 초기화 되지 않음")
    println(myLazyVal)

    myStr = "1차 값 변경"
    myStr = "2차 값 변경"

    println("myIntEven:$myIntEven")
    myIntEven = 6
    myIntEven = 3
    println("myIntEven:$myIntEven")

    myCounter = 2
    println("myCounter:$myCounter")
    myCounter = 5
    myCounter = 4
    println("myCounter:$myCounter")
    myCounter++
    myCounter--
    println("myCounter:$myCounter")
}