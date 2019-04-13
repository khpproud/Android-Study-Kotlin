package chap05

// 연산자 Invoke
// 1개의 인수를 취하는 함수
interface Function1<in P1, out R>: Function<R> {
    // 지정된 인자로 함수 호출
    operator fun invoke(p1: P1): R
}

enum class WolfActions {
    SLEEP, WALK, BITE
}

class Wolf1(val name: String) {
    operator fun invoke(action: WolfActions) = when (action) {
        WolfActions.SLEEP -> "$name 은 자는 중이다"
        WolfActions.WALK -> "$name 은 걷는 중이다"
        WolfActions.BITE -> "$name 은 물어뜯는 중이다"
    }
}

fun main() {
    val talbot = Wolf1("Talbot")
    // 아래는 동일
    println(talbot.invoke(WolfActions.BITE))
    println(talbot(WolfActions.BITE))
}