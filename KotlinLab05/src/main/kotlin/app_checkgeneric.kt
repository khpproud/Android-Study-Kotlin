package seventeen_five_one

// as 와 is
fun some(arg: Any) {
    if (arg is Int) {

    }
    val intVal = arg as Int
    intVal.plus(10)     // 예외 클래스 캐스트 예외 발생
}

// 제네릭에서 is 사용
fun some2(arg: List<Int>) {
    if (arg is List<Int>) {
        println(arg.sum())
    }
}

fun some3(arg: List<*>) {
    //if (arg is List<Int>) { } // 에러 컴파일 시 제거되는 타입을 점검할 수 없음
}

// 제네릭에서 as 사용
fun some4(arg: List<*>) {
    val intList = arg as List<Int>
    println(intList.sum())
}

// inline 과 reified 이용 - 실행시점까지 제네릭 타입 유지
inline fun <reified T> some5(arg: Any) {
    if (arg is T) {
        println("true")
    } else {
        println("false")
    }
}

fun main() {
    some(10)
    //some("Hello")

    some4(listOf(10, 20))
    //some4(listOf("Hello", "World"))   // 예외 클래스 캐스트 예외 발생

    some5<String>("Hello")
    some5<Int>("Hello")
}

