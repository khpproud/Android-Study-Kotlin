package four
// 가시성 한정자 사용 예
// top 레벨

public val version: String = "3.5.0"                // 모든 파일에서 이 속성에 접근할 수 있음

internal class UnitConverter

private fun printSomething() {
    println("Something")
}

fun main() {
    println(version)
    UnitConverter()
    printSomething()
}