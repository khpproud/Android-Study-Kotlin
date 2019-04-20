package chap06

// null을 가진 객체 목록의 정렬
class Person1(var age: Int?)

fun main() {
    val listOfPerson = listOf(Person1(91), Person1(43), Person1(28), Person1(null))

    // compareBy 안에 null의 위치 조건 함수 지정 nullsFirst or nullsLast
    val sortedList = listOfPerson.sortedWith(compareBy(nullsLast<Int>()) {
        it.age
    })
    sortedList.forEach { println(it.age) }
}