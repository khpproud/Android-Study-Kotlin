package chap04

// 함수 리터럴 예
fun main() {
    var str1 = "The start of a "
    // 리시버의 값에 문자열을 더하여 새로운 문자열을 반환
    val addStr = fun String.(successor: String): String {
        return this + successor
    }
    // 함수 리터럴은 호출된 리시버에 대한 접근 권한을 가지며 해당 리시버와 연결된 메소드에 접근 할 수 있다
    str1 = str1.addStr("beautiful day!!!")
    println(str1)

    // 첫 번째 매개변수로 리시버를 받는 일반 함수에 매개변수로 리시버를 전달할 수 있다
    // 이는 멤버함수가 아닌 함수를 사용하는 상황에서 유용하게 쓰임
    // String.(String) -> Int => (String, String) -> Int 와 비슷
    // receiver.functionLiteral(arguments) -> ReturnType
    str1 = "The start of a "
    val addStrLength = fun String.(successor: String): Int {
        return this.length + successor.length
    }
    var x = str1.addStrLength("beautiful day!!!")
    println("total length : $x")

    fun testIfEqual(op: (String, String) -> Int,
                    a: String, b: String, c: Int) = assert(op(a, b) == c)

    testIfEqual(addStrLength, str1, "beautiful day!!!", str1.length + "beautiful day!!!".length)
}