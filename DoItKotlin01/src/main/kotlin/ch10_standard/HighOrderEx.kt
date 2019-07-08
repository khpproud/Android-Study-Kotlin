package ch10_standard

// 고차 함수 복습
fun inc(x: Int): Int = x + 1

fun high(name: String, body: (Int) -> Int): Int {
    println("name: $name")
    val x = 0
    return body(x)
}

fun main() {
    val result = high("Sean", {x -> inc(x + 3)})         // 함수를 이용한 람다식
    val result2 = high("Sean") { inc(it + 3) }           // 소괄호 바깥으로 빼내고 생략
    val result3 = high("Kim", ::inc)                        // 메서드 레퍼런스
    val result4 = high("Sean") { x -> x + 3 }               // 람다식 자체를 넘겨줌
    val result5 = high("Kim") { it + 3 }                    // 매개변수가 1개인 경우 생략

    println(result)
    println(result2)
    println(result3)
    println(result4)
    println(result5)
}