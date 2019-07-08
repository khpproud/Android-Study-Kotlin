package ch09_collection

// 단순 메서드 체이닝 및 asSequence() 사용
fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val listDefault = list1.map { println("map($it) "); it * it }
        .filter { println("filter($it) "); it % 2 == 0 }
    println(listDefault)

    // asSequence()를 통해 가져오기
    val listSeq = list1.asSequence()
        .map { print("map($it) "); it * it }
        .filter { println("filter($it) "); it % 2 == 0 }
        .toList()
    println(listSeq)
}