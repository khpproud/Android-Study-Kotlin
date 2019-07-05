package chp07_variable_class

// 실드 클래스 사용 예1
//sealed class Result {
//    open class Success(val message: String) : Result()
//    class Error(val code: Int, val message: String) : Result()
//}
//
//class Status : Result()                             // 실드 클래스 살속은 같은 파일에서만 가능
//class Inside : Result.Success("Status")             // 내부 클래스 상속

// 실드 클래스 사용 예2
sealed class Result

open class Success(val message: String) : Result()
class Error(val code: Int, val message: String) : Result()

class Status : Result()
class Inside : Success("Status")

fun main() {
    val result = Success("Good!!")
    val msg = eval(result)
    println(msg)
}

// 상태를 검사하기 위한 함수
fun eval(result: Result): String = when (result) {
    is Status -> "in progress"
    is Success -> result.message
    is Error -> result.message
    // 모든 조건을 가지므로 else 가 필요 없음
}