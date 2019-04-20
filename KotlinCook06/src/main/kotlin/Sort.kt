package chap06

// comparator로 리스트 정렬
class Person(var age: Int)

fun main() {
    val p1 = Person(91)
    val p2 = Person(35)
    val p3 = Person(72)
    val listOfPerson = listOf(p1, p2, p3)
    var sortedListOfPerson = listOfPerson.sortedBy {
        it.age
    }

    sortedListOfPerson.forEach {
        println(it.age)
    }

    // sortedWith : 비교자를 직접 구현
    var sortedListOfPerson2 = listOfPerson.sortedWith(Comparator<Person> { o1, o2 ->
        if (o1.age > o2.age)
            1
        else if (o1.age < o2.age)
            -1
        else
            0
    })

    // 내림차순으로 정렬
    val listOfInt = listOf(1, 2 ,3 ,4, 5)
    var sortedList = listOfInt.sortedDescending()
    println(sortedList)

    val sortedListOfPerson3 = listOfPerson.sortedByDescending {
        it.age
    }
    sortedListOfPerson3.forEach {
        println(it.age)
    }
}