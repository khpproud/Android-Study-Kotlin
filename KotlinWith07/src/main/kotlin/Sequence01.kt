package seven.sequence

// 시퀀스 사용 예
fun main() {
    val numbers = generateSequence(1) { it + 1 }
        .map { it * 2 }
        .take(10)
        .toList()

    println(numbers)

    // 모든 중간 작업은 최종 작업전 까지 지연
    val seq = generateSequence(1) { println("Generated ${it + 1}"); it + 1 }
        .filter { println("Processing of filter: $it"); it % 2 == 1 }
        .map { println("Processing map: $it"); it * 2 }
        .take(2)

    seq.toList()

    // 표준 리스트처리는 작업 순서 시퀀스와 다르다
    (1.. 4).onEach { println("Generated $it") }
        .filter { println("Processing filter: $it"); it % 2 == 1 }
        .map { println("Processing map: $it"); it * 2 }
    
}