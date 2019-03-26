package five.lambda

// 코틀린의 람다 식(closure)
var a: (Int) -> Int = { i: Int -> i * 2 }
var b: () -> Int = { 4 }
var c: (String) -> Unit = { s: String -> println(s) }

// 람다식의 마지막 문의 값이 반환되며, 라벨로 한정된 return문이 있는 경우가 아니라면 return은 허용되지 않음
var aa: (Int) -> Int = { i -> /*return*/ i * 2 }
var l :(Int) -> Int = l@ { i -> return@l i * 2 }

// 여러행으로 표기 예
val printAndReturn = { i: Int, j: Int ->
    println("I calculate $i + $j")
    i + j
}

// 여러개의 문을 한 행으로 정의할 땐 세미콜론 이용
val printAndReturn2 = { i: Int, j: Int ->
    println("I calculate $ $i + $j"); i + j
}

fun main() {
    // 람다식이 인수로 제공된 값을 대상으로만 작업해야 되는 것은 아님
    // 람다식이 생성된 컨텍스트에서 접근 가능한 모든 속성과 함수를 이용 가능
    val text = "Text"
    var a: () -> Unit = { println(text) }
    a()
    a()

    // 자바와의 차이점 !!!! 자바에선 람다에 사용되는 변수는 final 즉 변경이 불가능함
    // 로컬 변수를 감싸며 함수 본체 안에서 변경할 수 있게 해주는 람다식을 클로저라 함
    var i = 1
    val b: () -> Int = { ++i }
    println(i)          // 1
    println(b())        // 2
    println(i)          // 2
    println(b())        // 3
    println(i)          // 3

    //컨텍스트에서 인수의 형식을 유추할 수 있으면 지정하지 않아도 됨
    val c: (Int) -> Int = { i -> i * 2 }            // i의 유추된 형식은 Int
    val d: (String) -> Unit = { s -> println(s) }   // s의 유추된 형식은 String

    // 단일 매개변수의 암시적 이름
    val e: (Int) -> Int = { it * 2 }                // 매개변수가 하나만 있는 경우
    val f: (String) -> Unit = { println(it) }

    

}