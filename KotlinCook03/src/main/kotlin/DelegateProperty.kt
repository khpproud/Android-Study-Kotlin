package chap03

import kotlin.properties.Delegates

// 위임 속성 사용 예
// lazy
val lazyVal: String by lazy {
    println("늦은 초기화")
    "Hello"
}

// observable - 수정을 관찰
class Travel {
    var placeName: String by Delegates.observable("<>") {
        property, oldValue, newValue ->
        println("${property.name} : oldValue = $oldValue, newValue = $newValue")
    }
}

// vetoable - 수정의 조건 설정 가능
class Travel1 {
    var placeName: String by Delegates.vetoable("<>") {
        _, _, newValue ->
        if (!newValue.equals("Paris"))
            return@vetoable false
        true
    }
}

// map - 맵을 위임 속성 자체로서 사용
class Travel2(val map: Map<String, Any?>) {
    val placeName: String by map
}

// var 속성인 경우 MutableMap 사용
class Travel3(val map: MutableMap<String, Any?>) {
    var placeName: String by map
}

fun main() {
    println("초기화 전")
    println(lazyVal)

    val paris = Travel()
    paris.placeName = "Paris"
    paris.placeName = "Tokyo"

    val tokyo = Travel1()
    tokyo.placeName = "Paris"
    tokyo.placeName = "Tokyo"
    println(tokyo.placeName)

    val seoul = Travel2(mapOf("placeName" to "Seoul"))
    println(seoul.placeName)

    val newYork = Travel3(mutableMapOf("placeName" to "NewYork"))
    println(newYork.placeName)
}