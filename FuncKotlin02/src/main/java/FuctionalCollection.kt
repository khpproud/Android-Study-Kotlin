package chap02.collection

// 함수적 콜렉션 사용 예
val numbers: List<Int> = listOf(1, 2, 3, 4)
fun main() {
    numbers.forEach { i -> println("i = $i")}

    val numbersTwice: List<Int> = listOf()

    //for (i in numbers)
        //numbersTwice.add(i * 2)               // 컴파일 에러 불변 리스트는 add 제공 안됨

    val numbersTwiceMutable: MutableList<Int> = mutableListOf()
    for (i in numbers)
        numbersTwiceMutable.add(i)              // 가변 리스트는 추가하는데 문제 없음

    // map() 함수 사용
    val numbersTwiceMap: List<Int> = numbers.map { i -> i * 2 }

    // sum()
    val sum = numbers.sum()
    println(sum)

    // fold() 사용
    val sumFold = numbers.fold(0) { acc, i ->
        println("acc, i = $acc, $i")
        acc + i }
    println(sumFold)

    // reduce() 사용
    val sumReduce = numbers.reduce { acc, i ->
        println("acc, i = $acc, $i")
        acc + i }
    println(sumReduce)

}