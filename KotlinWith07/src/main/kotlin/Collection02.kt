package seven.collection.two

// 스트림 컬렉션 처리 예
data class Student(
    val name: String,
    val grade: Double,
    val passing: Boolean
)

val students = listOf(
    Student("John", 4.2, true),
    Student("Bill", 3.5, true),
    Student("John", 3.2, false),
    Student("Aron", 4.3, true),
    Student("Jimmy", 3.1, false),
    Student("Homer", 2.9, false)
)

val bestStudents = students.asSequence().filter { it.passing }
    .withIndex()
    .sortedBy { it.value.grade }
    .take(3)
    .sortedBy { it.index }
    .map { it.value }.toList()

fun main() {
    // 이름과 리스트를 출력
    println(bestStudents.map { it.name })
}