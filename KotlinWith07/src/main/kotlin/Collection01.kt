package seven.collection

fun main() {
    // map
    var list = listOf(1, 2, 3).map { it * 2 }
    println(list)

    // filter
    list = listOf(1, 2, 3, 4, 5).filter { it > 2 }
    println(list)

    // flatMap
    list = listOf(10, 20).flatMap { listOf(it, it + 1, it + 2) }
    println(list)

    // forEach
    listOf("A", "B", "C").forEach { print(it) }
    println()

    // onEach : 내부적으로 apply한 리시버를 반환
    (1.. 10).filter { it % 3 == 0 }
        .onEach(::print)
        .map { it / 3 }
        .forEach(::print)
    println()

    // withIndex : 리스트의 요소 인덱스를 기준으로 처리
    listOf(9, 8, 7, 6).withIndex()
        .filter { (i, _) -> i % 2 == 0 }
        .forEach { (i, v) -> print("$v at $i, ") }
    println()

    // indexed 변형
    val list2 = listOf(10, 10, 10)
        .mapIndexed { index, i -> index * i }
    println(list2)

    val list3 = listOf(1, 4, 9).forEachIndexed{ index, i -> print("$index: $i, ")}
    println(list3)

    // sum
    println(listOf(1, 2, 3, 4).sum())

    // sumBy
    class User(val points: Int)
    val users = listOf(User(10), User(1_000), User(10_000))
    var points = users.map { it.points }.sum()
    println(points)

    points = users.sumBy { it.points }
    println(points)

    // count
    val evens = (1.. 5).count { it % 2 == 1 }
    val odds = (1.. 5).count { it % 2 == 0 }
    println(evens)
    println(odds)

    // min, max
    val list4 = listOf(4, 2, 5, 1)
    println(list4.min())
    println(list4.max())

    val strs = listOf("kok", "ada", "bal", "mal")
    println(strs.min())

    // sorted
    println(strs.sorted())

    // String을 이용해 순서를 지정
    val list5 = listOf(14, 31, 2)
    println(list5.sortedBy { "$it" })

    // 선택자를 기준으로 최소 및 최대 요소를 찾음
    val minByLen = listOf("ppp", "z", "as").minBy { it.length }
    println(minByLen)

    val maxByLen = listOf("Foul", "HomeRun"," Hit").maxBy { it.length }
    println(maxByLen)

    // Comparator를 정의해 비교
    val comp = Comparator<String> { e1, e2 -> e1.length - e2.length }
    val minByLenComp = listOf("ppp", "z", "as").sortedWith(comp)
    println(minByLenComp)

    // compareBy - Comparator 생성을 간소화하기 위해 제공됨
    data class Student(val name: String, val surname: String) {
        override fun toString() = "$name $surname"
    }

    val students = listOf(
        Student("Bart", "Simpson"),
        Student("Donald", "Trump"),
        Student("Abe", "Kim"),
        Student("Adam", "Simpson"),
        Student("Max", "Power")
    )
    var sortedStudents = students.sortedWith(compareBy ({ it.surname }, { it.name}))
    println(sortedStudents)

    // 람다식 대신 속성 참조 사용
    sortedStudents = students.sortedWith(compareBy(Student::surname, Student::name))
    println(sortedStudents)

    // groupBy
    val grouped = listOf("ala", "alan", "mulan", "malan").groupBy { it.first() }
    println(grouped)

    data class Grade(val name: String, val classCode: String, val meanGrade: Float)
    val students1 = listOf(
        Grade("Homer", "1", 1.1f),
        Grade("Carl", "2", 1.5f),
        Grade("Donald", "2", 3.5f),
        Grade("Alex", "3", 4.5f),
        Grade("Marcin", "3", 5.0f),
        Grade("Max", "1",3.2f)
    )

    val bestInClass = students1.groupBy { it.classCode }.onEach { println(it) }
        .map { (_, students) -> students.maxBy { it.meanGrade }!! }.onEach { println(it) }
        .map { it.name }
    println(bestInClass)

    // sort: 가변 객체의 내용을 직접 변경하며, Unit을 반환
    // sorted: 정렬된 컬렉션을 반환하며, 호출 대상 컬렉션을 직접 수정하지 않음
    val list6 = mutableListOf(3, 2, 4, 1)
    val list7 = list6.sorted()
    println(list6)
    println(list7)
    list6.sort()
    println(list6)

}