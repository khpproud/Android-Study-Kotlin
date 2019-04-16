package chap06

import java.text.SimpleDateFormat
import java.util.*

// 위임된 맵 : 함수/클래스 생성자에서 여러 파라미터 대신 하나의 파라미터로 맵 전달 가능
data class Book(val delegate: Map<String, Any?>) {
    val name: String by delegate
    val authors: String by delegate
    val pageCount: Int by delegate
    val publicationData: Date by delegate
    val publisher: String by delegate
}

fun main() {
    val map1 = mapOf(
        Pair("name", "슬램덩크"),
        Pair("authors", "만화가"),
        Pair("pageCount", 400),
        Pair("publicationDate", SimpleDateFormat("yyyy/mm/dd").parse("2019/04/15")),
        Pair("publisher", "만화"))

    val map2 = mapOf(
        "name" to "드래곤볼",
        "authors" to "누군가",
        "pageCount" to 300,
        "publicationDate" to SimpleDateFormat("yyyy/mm/dd").parse("2019/04/15"),
        "publisher" to "Comics"
    )

    val book1 = Book(map1)
    val book2 = Book(map2)
    println("Book1 $book1 \nBook2 $book2")
}