package chap04

//가변 인수를 함수에 전달하는 예
fun someMethod(vararg a: String) {
    for (a_ in a) {
        println(a_)
    }
}

//가변 인수외에 다른 변수를 함께 전달해야 하는 경우
fun someMethod(b: Int, vararg a: String) {
    println("b : $b")
    for (a_ in a)
        println(a_)
}

// 인수의 마지막위치에 가변인수가 아닌 다른 변수가 있을 경우 명명된 파라미터 사용
fun someMethod(b: Int, vararg a: String, c: String) {
    println("b : $b")
    for (a_ in a)
        println(a_)
    println("c : $c")
}

fun main() {
    someMethod("as", "you", "know", "this", "works")

    //이미 값 배열을 가지고 있다면 * (스프레드 연산자)를 이용하여 직접 값을 전달 가능
    val list = arrayOf("1", "2", "3", "4", "5")
    someMethod(*list)

    val list2 = arrayOf("one", "two", "three", "four")
    someMethod(2, *list)

    someMethod(2, "one", "two", "three", c = "4")
}