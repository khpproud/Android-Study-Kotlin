package chap05

// 중위 함수 사용 예(하나의 파라미터만 가진 경우)
infix fun Int.superOperation(i: Int) = this + i

// 다음과 같은 밈도 만들 수 있음
object All {
    infix fun your(base: Pair<Base, Us>) { }
}

object Base {
    infix fun are(belong: Belong) = this
}

object Belong

object Us

// 백틱(`) 으로 임의의 식별자를 사용 가능
infix fun String.`(╯°□°）╯︵ ┻━┻`(s: String) = "*$this 가 $s 의 테이블을 엎는다*"

fun main() {
    println(1 superOperation 2)
    println(1.superOperation(2))

    All your (Base are Belong to Us)

    println("Adam" `(╯°□°）╯︵ ┻━┻` "Ben")
}
