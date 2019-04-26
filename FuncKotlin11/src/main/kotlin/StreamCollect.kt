package chap11

import java.util.stream.Collectors.*
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.streams.asStream

// 스트림 수집 collect() 함수의 사용 예
fun main() {
    // toList 일반적인 리스트로 수집
    val resultList = (0.. 10).asSequence().asStream()
        .collect(toList())

    // 커스텀 컬렉션으로 생성
    val resultCollection = (0.. 10).asSequence().asStream()
        .collect(toCollection { LinkedHashSet<Int>() })
    println("result custom Collection $resultCollection")

    // 맵에 수집 : Collectors.toMap()
    val resultMap = (0.. 10).asSequence().asStream()
        .collect(toMap<Int, Int, Int>( {
            it
        }, { it * it }))
    println("result Map = $resultMap")

    // 문자열 스트림의 결합 : Collectors.joining()
    val resultString = Stream.builder<String>()
        .add("Item1")
        .add("Item2")
        .add("Item3")
        .add("Item4")
        .add("Item5")
        .build()
        .collect(joining(" - ", "Start => ", " <= End"))
    println(resultString)

    // 스트림 요소 그룹화 : Collectors.groupingBy()
    val resultGroup = (1.. 20).asSequence().asStream()
        .collect(groupingBy<Int, Int> { it % 5 })
    println("result Group $resultGroup")

}