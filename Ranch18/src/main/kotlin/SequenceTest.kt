import java.lang.Integer.sum
import kotlin.system.measureNanoTime

// 소수인지를 결정하는 Int 타입의 확장 함수
fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false
        }
    }
    return true
}

// 챌린지 : Map의 키와 값을 바꿔치기 하기
val gradesByStudent = mapOf("Josh" to 4.0, "Alex" to 2.0, "Jane" to 3.0)

fun <K, V> flipValues(mapData: Map<K, V>): Map<V, K> {
    return mapData.map { entry: Map.Entry<K, V> -> entry.value to entry.key }
        .toMap()
}

// 챌린지 : 슬라이딩 윈도우
/**
 * 1.단계 : 5보다 작은 수는 제외시킨다
 * 2.단계 : 인접한 두 개의 수를 한 쌍으로 묶는다
 * 3.단계 : 각 쌍의 수를 곱하다
 * 4.단계 : 모둔 수를 더해서 최종값을 산출한다
 * 아래 예 의 최종 값은 2,399
 */
val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)

val resultSlidingWindow =
    valuesToAdd.filter { it >= 5 }              // 1단계
        .windowed(size = 2, step = 2)           // 2단계
        .map { it[0] * it[1] }                  // 3단계
        .reduce(::sum)                          // 4단계

fun main() {
    // 컬렉션을 이용 검사할 항목이 미리 저장되 있어야 한다
//    val toList = (2.. 5000).toList().filter { it.isPrime() }.take(100)
//    println(toList)

    // List와 Sequence의 성능 측정
//    val listInNanos = measureNanoTime {
//        val oneThousandPrimesList = (2.. 7919).toList().filter { it.isPrime() }.take(1000)
//        println(oneThousandPrimesList)
//    }
//
//    val sequenceInNanos = measureNanoTime {
//        val oneThousandPrimesSequence = generateSequence(2) { value -> value + 1 }
//            .filter { it.isPrime() }.take(1000).forEach { print("$it ") }
//    }
//
//    println("List 작업 완료 소요 시간 : $listInNanos ns")
//    println("Sequence 작업 완료 소요 시간 : $sequenceInNanos ns")

    println(gradesByStudent)
    println(flipValues(gradesByStudent))

    println(resultSlidingWindow)
}