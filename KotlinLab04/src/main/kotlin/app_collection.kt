package fourteen_one

class User(val name: String, val age: Int)
var list = listOf(User("Park", 33), User("Lee", 26))

fun main() {
    listOf(1, 2, 3).forEachIndexed { index, value ->
        println("index : $index, value : $value")
    }

    // all(), any() 함수 테스트
    println("all test : ${list.all { it.age > 30 }}")
    println("any test : ${list.any { it.age > 30 }}")

    // count(), find() 함수 테스트
    println("count test : ${list.count { it.age > 25}}")
    val user = list.find { it.age > 25 }
    println("find test : ${user?.name} ... ${user?.age}")

    // fold()
    var result = listOf(1, 2).fold(10) { total, next ->
        println("$total ... $next")
        total + next
    }
    println("fold result : $result")

    // 최대 값 구하기 - fold() 이용
    result = listOf(1, 11, 5).fold(10) {
        max, next -> if (next > max) next else max
    }
    println("fold test : $result")

    // foldRight()
    result = listOf(1, 2, 3, 4).foldRight(5) {
        next: Int, total: Int ->
        println("$total ... $next")
        total + next
    }
    println("foldRight test : $result")

    // reduce()
    result = listOf(1, 2, 3).reduce {
        sum, next -> sum + next
    }
    println("reduce test : $result")

    // reduceRight()
    result = listOf(1, 2, 3, 4, 5).reduceRight { next, sum ->
        println("$next ... $sum")
        sum + next
    }
    println("reduceRight test : $result")

    // max()
    var result1: Int? = listOf(1, 12, 4).max()
    println("max test : $result1")

    // maxBy()
    result1 = listOf(1, 13, 6).maxBy {
        it % 10
    }
    println("maxBy test : $result1")

    // none()
    val result2 = listOf(1, 14, 6).none { it % 10 == 0 }
    println("none test : $result2")

    // sumBy()
    val result3 = listOf(1, 3, 5).sumBy {
        it * 10
    }
    println("sumBy test : $result3")
}

