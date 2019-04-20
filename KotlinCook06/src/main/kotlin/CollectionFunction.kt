package chap06

// 기타 컬렉션 함수
fun main() {
    // padStart : 인자로 설정한 문자만큼 나머지 문자를 채움
    val string = "abcdef"
    var pad = string.padStart(10, '-')
    println(pad)

    // padEnd
    pad = string.padEnd(10, '!')
    println(pad)

    // flatten : 컬렉션을 인자로 받은 후 컬렉션의 모든 아이템을 하나의 리스트로 합쳐서 반환
    val a = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    a.flatten().forEach {
        print("$it ")
    }
    println()

    // compareBy( { field1 }, {field2 }, ... } 로 필드1, 필드2, 필드... 순으로 정렬
    val studentList = listOf(Student(22, 3.5), Student(21, 4.2),
        Student(22, 3.3), Student(22,2.9))
    val sortedList = studentList.sortedWith(compareBy( { it.age }, { it.GPA }))
    sortedList.forEach {
        println("age: ${it.age}, GPA : ${it.GPA}")
    }

    // 리스트에서 limit한 선택
    // take
    val list = listOf(1, 2, 3, 4, 5)
    var limitedList = list.take(3)
    println(limitedList)
    // takeLast
    limitedList = list.takeLast(2)
    println(limitedList)
    // takeWhile
    limitedList = list.takeWhile { it < 3 }
    println(limitedList)
    // takeLastWhile
    limitedList = list.takeLastWhile { it > 3 }
    println(limitedList)
    // takeIf
    limitedList = list.takeIf { it.contains(3) }!!
    println(limitedList)

    // drop : 처음 n개의 항목을 버리고 새로운 컬렉션 반환
    var droppedList = list.drop(2)
    droppedList.forEach { print("$it ") }
    println()

    // dropLast
    droppedList = list.dropLast(4)
    println(droppedList)

    // dropWhile : 조건이 충족되는 동안 컬렉션의 아이템 삭제
    droppedList = list.dropWhile { it < 3 }
    println(droppedList)

    // dropLastWhile
    droppedList = list.dropLastWhile { it > 3 }
    println(droppedList)
}

class Student(val age: Int, val GPA: Double)
