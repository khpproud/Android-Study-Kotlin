package ch10_standard

// apply() 함수와 run() 함수 비교
fun main() {
    data class Person(var name: String, var skills: String)
    var person = Person("Kim", "Kotlin")
    val returnObj = person.apply {
        this.name = "Sean"
        this.skills = "Java"
        "success"                               // 사용되지 않음
    }
    println(person)
    println("returnObj : $returnObj")

    val returnObj2 = person.run {
        this.name = "Park"
        this.skills = "C#"
        "success"
    }
    println(person)
    println("returnObj : $returnObj2")
}