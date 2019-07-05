package ch04_flowcontrol.flow

// 사용자 예외 클래스 만들기 예
class InvalidNameException(message: String): Exception(message)

fun main() {
    var name = "Koo123"

    try {
        validName(name)
    } catch (e: InvalidNameException) {
        println(e.message)
    } catch (e :Exception) {
        println(e.message)
    }
}

fun validName(name: String) {
    if (name.matches(Regex(".*\\d+.*"))) {      // 이름에 숫자가 포함되어 있으면 예외 발생
        throw InvalidNameException("Your name : $name : contains numbers!")
    }
}