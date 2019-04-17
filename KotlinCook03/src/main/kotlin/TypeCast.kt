package chap03

// 코틀린에서 캐스트 연산자 is, as 사용 예
fun main() {
    // 안전한 캐스트 키워드 is
    var a: Any = 1
    if (a is String)
        println("$a is String")
    else
        println("$a is not String")

    // 안전하지 않은 캐스트 키워드 as
    var c: Any = 1
    var d = c as String
}