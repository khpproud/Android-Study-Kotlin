/**
 * 코틀린의 확장 함수 제공 기능 활용 예
 */
fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

// 슈퍼 클래스에 확장 함수 정의하기
//fun Any.easyPrint() = println(this)

// 제네릭 확장 함수
fun <T> T.easyPrint(): T {
    println(this)
    return this
}

// 코틀린 표준 라이브러리의 let 함수 예
private inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
}

// 확장 속성 : backing field를 갖지 못하므로 val만 사용할 수 있고 get을 반드시 정의
val String.numVowels
    get() = count { "aeiouy".contains(it) }

// null 가능 타입에 확장 함수 추가
infix fun String?.printWithDefault(default: String) = print(this ?: default)

fun main() {
    println("Say Ho".addEnthusiasm(4))

    "Say Ho".addEnthusiasm().easyPrint()
    42.easyPrint()

    "Say Ho".easyPrint().addEnthusiasm().easyPrint()

    "How many vowels?".numVowels.easyPrint()

    val nullableString: String? = null
    nullableString printWithDefault "기본 문자열\n"
    nullableString.printWithDefault("기본 문자열\n")
}