package chap05

// 함수의 마지막 파라미터가 람다인 경우 람다 자체가 괄호 바깥으로 전달될 수 있음
fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition)
        block()
}

// vararg 와 Lambda 의 결합 예
fun <T, R> transform(vararg ts: T, f: (T) -> R): List<R> = ts.map(f)

// 다음 예는 조금 복잡
fun <T> emit(t: T, vararg listener: (T) -> Unit) = listener.forEach {
    it(t)
}

fun main() {
    val someBoolean = false
    unless(someBoolean) {
        println("Do not access this web site!!!")
    }

    println(transform(1, 2, 3, 4) { i: Int -> i.toString() })

    //컴파일 에러 : vararg 로 값을 전달하는 것은 괄호로 묶인 인수 목록내에서만 허용
    //emit(1) { i -> println(i) }

    // 내부로 전달은 가능함
    emit(1, ::println, { i -> println(i * 2)})
}
