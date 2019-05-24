import kotlin.math.sqrt

/**
 * 함수형 프로그래밍 방식이 코틀린에 적용된 예
 */
// 1.변환 함수(Transform Function) 예
val tenDollarWords = listOf("auspicious", "avuncular", "obviate")
val tenDollarWordLength = tenDollarWords.map { it.length }

/**
 * map 확장 함수 형식
 */
// <T, R> Iterable<T>.map2(transform: (T) -> R): List<R>

// 2.필터 함수(Filter Function) 예
// 소수 추출하기
val numbers = listOf(7, 4, 8, 4, 3, 22, 17, 11)
val primes = numbers.filter { number ->
    (2.. sqrt(number.toFloat()).toInt()).map { number % it }
        .none { it == 0 }
}

// 3. 결합 함수(Combining Function) 예
val employees = listOf("Denny", "Claudette", "Peter")
val shirtSize = listOf("large", "x-large", "medium")
val employeeShirtSize = employees.zip(shirtSize).toMap()

fun main() {
    println(tenDollarWords.size)
    println(tenDollarWordLength.size)
    println(primes)
    println(employeeShirtSize["Denny"])

    // fold
    val foldedValue = listOf(1, 2, 3, 4).fold(0) { acc: Int, i: Int ->
        println("Accumulated value : $acc")
        acc + (i * 3)
    }
    println("Final value: $foldedValue")

    // 연쇄 호출
    val formattedOrders = employees.zip(shirtSize).toMap()
        .map { "${it.key}, shirt size: ${it.value}" }
    
    println(formattedOrders)
}

