package chap02.first

// 코틀린의 일급 함수
val capitalize = { str: String -> str.capitalize() }

// 아래와 메서드와 동일
val capitalizeFunction = object: Function1<String, String> {
    override fun invoke(p1: String): String {
        return p1.capitalize()
    }
}

// 람다 함수는 다른 함수의 파라미터로도 사용 가능
fun <T> transform(t: T, fn: (T) -> T): T {
    return fn(t)
}

// 메서드 레퍼런스로 전달하기 위한 함수 예
fun reverse(str: String): String {
    return str.reversed()
}

// Object의 함수 참조
object MyUtils {
    fun doNothing(str: String): String {
        return str
    }
}

// Class의 함수 참조
class Transformer {
    fun upperCased(str: String): String {
        return str.toUpperCase()
    }

    companion object {
        fun lowerCased(str: String): String {
            return str.toLowerCase()
        }
    }
}

fun main() {
    println(capitalize("hello function"))
    println(capitalizeFunction("hello function"))

    println(transform("kotlin", capitalize))
    // 메서드 레퍼러스로도 참조 가능
    println(transform("kotlin", ::reverse))
    println(transform("kotlin", MyUtils::doNothing))

    val transformer = Transformer()
    println(transform("kotlin", transformer::upperCased))
    println(transform("kotlin", Transformer.Companion::lowerCased))

    // 인스턴스나 Companion Object 메서드에 참조를 전달
    println(transform("king", { str -> str.substring(0.. 1)}))
    println(transform("queen", { it.substring(0.. 2)}))

    // 마지막 파라미터를 람다로 받으면 괄호는 밖으로 전달 가능
    println(transform("jack") {str -> str.toUpperCase()})
    // 메서드 레퍼런스로도 변환 가능
    println(transform("thirteen", String::toUpperCase))
}

