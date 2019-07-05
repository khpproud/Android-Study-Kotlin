package ch06.property

import kotlin.properties.Delegates

// observable() 함수 사용 예
class User3 {
    var name: String by Delegates.observable("NONAME") {        // 프로퍼티 위임
        property, oldValue, newValue ->                                   // 람다식의 매개변수로 프로퍼티, 기존값, 새 값 지정
        println("$oldValue -> $newValue")                                 // 이 부분은 이벤트가 발생할 때만 실행
    }
}

fun main() {
    val user = User3()
    user.name = "Koo"
    user.name = "Kim"
}