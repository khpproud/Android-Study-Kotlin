package ch06.property

// by lazy 로 선언된 객체 지연 초기화하기
class Person2(val name: String, val age: Int)

fun main() {
    var isPersonInstantiated: Boolean = false           // 초기화 확인용

    val person: Person2 by lazy {                       // lazy 를 사용한 person 객체의 지연 초기화
        isPersonInstantiated = true
        Person2("Kim", 23)
    }

    val personDelegate = lazy { Person2("Hong", 40) }

    println("person Init : $isPersonInstantiated")
    println("personDelegate Init : ${personDelegate.isInitialized()}")

    println("person.name = ${person.name}")             // 이 시점에서 초기화
    println("personDelegate.value.name : ${personDelegate.value.name}")   // 이 시점에서 초기화

    println("person Init : $isPersonInstantiated")
    println("personDelegate Init : ${personDelegate.isInitialized()}")
}