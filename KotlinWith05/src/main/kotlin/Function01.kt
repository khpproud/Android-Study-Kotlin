package five

val a: (Int) -> Unit = { num -> println(num) }

fun main() {
    a(10)
    // 위와 동일
    a.invoke(10)

    // 익명 함수 사용 예 (fun 키워드와 매개변수 사이에 이름이 없음)
    val a: (Int) -> Int = fun(i: Int) = i * 2
    val b: () -> Int = fun(): Int { return 4}
    val c: (String) -> Unit = fun(s: String) { println(s) }

    // 함수 형식 유추
    var aa = fun(i: Int) = i * 2
    var bb = fun():Int { return 4 }
    var cc = fun(s: String) { println(s) }

    // 반대로도 유추 가능
    var aaa: (Int) -> Int = fun(i) = i * 2
    var ccc: (String) -> Unit = fun(s) { println(s) }

    println(a(10))
    println(b())
    c("Kotlin rules!!!")

    // invoke 사용 - 연산자 함수이며 함수 호출과 동일한 방식으로 이용
    println(a.invoke(4))
    println(b.invoke())
    c.invoke("Hello, Kotlin!!!")

    // 안전 호출을 위해 invoke 메소드 이용
    var d: ((Int) -> Int)? = null               // 변수 d는 null 허용이며 안전 호출을 통해 invoke를 호출
    if (false) d = fun(i) = i * 2
    println(d?.invoke(4))                       // 출력 null

    if (true) d = fun(i) = i * 2
    println(d?.invoke(4))                       // 출력 8
}