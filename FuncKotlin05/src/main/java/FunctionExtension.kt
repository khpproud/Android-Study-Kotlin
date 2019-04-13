package chap05

// 확장 함수의 예
fun String.sendToConsole() = println(this)

// String 은 리시버 타입 이며 아래와 동일 (첫 번째 파라미터)
fun sendToConsole1(string: String) = println(string)

fun main() {
    "Hello kotlin!!!".sendToConsole()

    sendToConsole1("Hello kotlin!!!")
}

// 확장함수는 this 의 private 멤버에 접근할 수 없음
class Human(private val name: String)

// 컴파일 에러 : name은 private 임
//fun Human.speak(): String = "${this.name}"