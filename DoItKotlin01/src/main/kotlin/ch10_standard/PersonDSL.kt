package ch10_standard

// Person 을 위한 DSL 만들어 보기
data class Person(
    var name: String? = null,
    var age: Int? = null,
    var job: Job? = null)


data class Job(
    var category: String? = null,
    var position: String? = null,
    var extension: Int? = null)

fun person(block: Person.() -> Unit): Person = Person().apply(block)

fun Person.job(block: Job.() -> Unit) {
    job = Job().apply(block)
}

fun main() {
    val person = person {
        name = "Koo"
        age = 40
        job {
            category = "IT"
            position = "Android Developer"
            extension = 1234
        }
    }
    println(person)
}