package ch10_standard

// 클로저 테스트 하기
fun main() {
    val calc = Calc()
    var result = 0                                             // 외부의 변수
    calc.addNum(2, 3) { x, y -> result = x + y }        // 클로저
    println(result)

    filteredNames(4)
}

class Calc {
    fun addNum(a: Int, b: Int, add: (Int, Int) -> Unit) {      // 람다식 add 에는 반환값이 없음
        add(a, b)
    }
}

// 길이가 일치하는 이름만 반환
fun filteredNames(length: Int) {
    val names = arrayListOf("Kim", "Hong", "Go", "Park", "Hwang", "Jeon")
    val filterResult = names.filter {
        it.length == length                                     // 바깥의 length 에 접근
    }
    println(filterResult)
}