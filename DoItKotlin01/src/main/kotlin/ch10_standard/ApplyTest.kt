package ch10_standard

// apply() 함수 예
fun main() {
    data class Person(var name: String, var skills: String)
    var person = Person("Kim", "Kotlin")
    person.apply { this.skills = "Swift" }                  // 여기서 this 는 person 객체
    println(person)

    val returnObj = person.apply {
        name = "Sean"                                       // this 는 생략 가능
        skills = "Java"                                     // this 없이 객체의 멤버에 여러번 접근
    }
    println(person)
    println(returnObj)
}