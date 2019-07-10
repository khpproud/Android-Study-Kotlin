package ch10_standard

// let() 함수와 also() 함수 비교
fun main() {
    data class Person(var name: String, var skills: String)
    var person = Person("Koo", "Kotlin")
    val a = person.let {
        it.skills = "Android"
        "success"
    }
    println(person)
    println("a : $a")
    val b = person.also {
        it.skills = "Java"
        "success"                           // 마지막 문장은 사용되지 않음
    }
    println(person)
    println("b : $b")
}