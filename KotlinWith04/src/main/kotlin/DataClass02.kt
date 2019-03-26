package four

// 구조 분해 선언(destructuring declarations)
data class Person(val firstName: String, val lastName: String, val height: Int)

fun main() {
    val person = Person("Bart", "Simpson", 180)
    var (firstName, lastName, height) = person
    println(firstName)
    println(lastName)
    println(height)
    // 컴파일러에서 생성된 코드
    println(person.component1())
    println(person.component2())
    println(person.component3())

    // String 같은 단순 형식도 분해 가능
    val fileName = "MainActivity.kt"
    val (name, extension) = fileName.split(".", limit = 2)

    // for 루프에도 사용 가능
    val authors = listOf(
        Person("Lisa", "Simpson", 160),
        Person("Homer", "Simpson", 170)
    )

    println("Authors : ")
    for ((name, surname) in authors)
        println("$name $surname")
}