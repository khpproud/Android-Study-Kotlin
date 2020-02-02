package appA

import java.util.stream.Stream

@Repeatable
@Retention(AnnotationRetention.RUNTIME)
annotation class Author(val name: String)

@Retention(AnnotationRetention.RUNTIME)
annotation class Authors(vararg val value: Author)

@Authors(Author("Raoul"), Author("Mario"), Author("Alan"))
class Book

/**
 * Kotlin has not yet supported Repeatable Annotations in Runtime Retention
 */
//@Author("Raoul")
//@Author("Mario")
//@Author("Alan")
//class Book

fun main() {
//    val authors = Book::class.java.getAnnotationsByType(Authors::class.java)

    val authors = Book::class.java.getAnnotation(Authors::class.java)
//    authors.value.forEach { a -> println(a.name) }
    Stream.of(*authors.value).forEach { a -> println(a.name) }
}