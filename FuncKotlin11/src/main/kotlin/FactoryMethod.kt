package chap11

import java.util.stream.Collectors.toList
import java.util.stream.Stream

// 스트림 팩토리 메소드
fun main() {
    // 스트림 빌더
    val stream1 = Stream.builder<String>()
        .add("Item1")
        .add("Item2")
        .add("Item3")
        .add("Item4")
        .add("Item5")
        .build()
    println("스트림 ${stream1.collect(toList())}")

    // 빈 스트림 생성: Stream.empty()
    val emptyStream = Stream.empty<String>()
    val item = emptyStream.findAny()
    println("Item $item")

    // 요소를 전달해 스트림 만들기: Stream.of()
    val stream2 = Stream.of("Item1", "Item2", 3, 4.5, "Item 5")
    println("Items in Stream = ${stream2.collect(toList())}")

    // 스트림 생성 : Stream.generate()
    val stream3 = Stream.generate {
        // 난수를 반환
        (1.. 20).random()
    }

    val resultList = stream3
        .limit(10)
        .collect(toList())
    println("resultList = $resultList")
}