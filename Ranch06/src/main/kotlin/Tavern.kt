
fun main() {
    // null 가능 변수 정의하기
//    var beverage = readLine()
//    beverage = null
//    println(beverage)

    // null 가능 변수 사용하기
    // 1.안전 호출 연산자 사용(Safe call operator) - ?.
//    var beverage = readLine()?.capitalize()
//    println(beverage)

    // 안전 호출 연산자와 함께 let 사용하기
//    var beverage = readLine()?.let {
//        if (it.isNotBlank()) {
//            it.capitalize()
//        } else {
//            "Beer"
//        }
//    }
//    println(beverage)

    // 2. non-null 단언 연산자 사용(double bang) - !!
//    var beberage2 = readLine()!!.capitalize()
//    println(beberage2)

    // 3.값이 null인지 if로 검사하기
//    var beverage3 = readLine()
//
//    if (beverage3 != null) {
//        beverage3 = beverage3.capitalize()
//    } else {
//        println("beverage가 null임!!!")
//    }
//
//    println(beverage3?.capitalize()?.plus(", large"))

    // null 복합 연산자 (a.k.a. 엘비스 연산자) 사용 - ?:
    var beverage4 = readLine()
    var beverageServed: String = beverage4?: "beer"
    println(beverageServed)
}