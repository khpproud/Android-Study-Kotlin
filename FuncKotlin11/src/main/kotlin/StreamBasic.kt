package chap11

import java.util.stream.Collectors.toList
import kotlin.streams.asStream

// 스트림 소개
fun main() {
    val sequence = 1.rangeTo(10).asSequence()
    var stream = sequence.asStream()
    val resultantList = stream.skip(5).collect(toList())
    println(resultantList)

    stream = sequence.asStream()
    val evenList = stream.filter {
        it % 2 == 0
    }.collect(toList())
    println(evenList)
}