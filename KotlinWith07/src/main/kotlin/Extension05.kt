package seven.five

import java.util.*

// 제네릭 확장 함수
val <T> List<T>.lastIndex : Int
get() = size - 1

// 컬렉션 처리
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
    Student("Jimmy", 3.1, false)
)

// 합격한 학생 중 상위 3명만 출력할 때
fun main() {
    // 명령식 접근법(반복문과 정렬 메소드를 사용하는 방식)
    val filteredList = ArrayList<Student>()
    for (student in students) {
        if (student.passing)
            filteredList += student
    }

    filteredList.sortWith(Comparator { p1, p2 -> if (p1.grade > p2.grade) -1 else 1 })

    println(filteredList)

    // 코틀린의 스트림 처리 이용
    students.filter { it.passing }
        .sortedByDescending { it.grade }
        .take(3)
        .forEach(::println)
}