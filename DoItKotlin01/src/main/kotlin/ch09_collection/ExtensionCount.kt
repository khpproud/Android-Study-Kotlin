package ch09_collection

// 컬렉션의 요소 집계 확장 함수 사용 예
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    val listPair = listOf(Pair("A", 300), Pair("B", 200), Pair("C", 100))
    val map = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")

    // 요소의 순환 forEach, forEachIndexed
    list.forEach { print("$it ") }
    println()
    list.forEachIndexed { index, value -> println("index[$index] : $value") }

    // onEach : 각 요소를 람다식으로 처리하고 각 컬렉션을 반환 받음
    val returnedList = list.onEach { print(it) }
    println()
    val returnedMap = map.onEach { println("key: ${it.key}, value: ${it.value}") }
    println(returnedList)
    println(returnedMap)

    // 요소의 개수 반환하기 count
    println(list.count { it % 2 == 0 })

    // 최댓값과 최솟값의 요소 반환 max, min, maxBy, minBy
    println(list.max())
    println(list.min())

    println("maxBy: ${map.maxBy { it.key }}")               // 키를 기준으로 최댓값
    println("minBy: ${map.minBy { it.key }}")               // 키를 기준으로 최솟값

    // 각 요소에 정해진 식 적용 fold, reduce
    // fold : 초깃값과 정해진 식에 따라 처음 요소부터 끝 요소에 적용하여 값 생성
    println(list.fold(4) { total, next -> total + next} )   // 4 + 1 + ... + 6 = 25
    println(list.fold(1) { total, next -> total * next} )   // 1 * 1 * ... * 6 = 720

    // foldRight : fold 와 같고 마지막 요소에서 처음 요소로 반대로 적용
    println(list.foldRight(4) { total, next -> total + next} )
    println(list.foldRight(1) { total, next -> total * next} )

    // reduce : fold 와 동일하지만 초깂값을 사용하지 않음
    println(list.reduce { total, next -> total + next} )
    println(list.reduceRight { total, next -> total * next} )

    // 모든 요소 합산하기 sumBy
    println(listPair.sumBy { it.second })                   // listPair 의 모든 두 번재 값을 더함
}