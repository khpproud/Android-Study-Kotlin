// 람다식의 함수 대입
val funcVal1 = {
    x1: Int -> println("Hello world!!!")
    x1 * 10
}

// Function Reference 대입
fun someFun() {
    println("I am som Function!!!")
}

val funcVal2 = ::someFun

// 일반 함수 정의
fun sum(x1: Int, x2: Int): Int {
    return x1 + x2
}

// 람다 함수로 정의
val sum1 = { x1: Int, x2: Int -> x1 + x2 }

// 매개변수 없는 람다 함수 '->' 생략
val sum2 = { 10 + 20 }

// 여러 줄의 함수 내용 (마지막줄이 반환값)
val sum3 = {
    x1: Int, x2: Int ->
    println("call sum3()...")
    x1 + x2
}

// 함수 타입 선언
val lambdaFun: (Int) -> Int = { x -> x * 10 }

// typealias 을 이용한 타입 선언
typealias MyType = (Int) -> Boolean

val myFun: MyType = { it > 10 }

// 타입 생략
//val lambdaFun1 = { x -> x * 10} // 에러
val lambdaFun2: (Int) -> Int = { x -> x * 10 }

// it 활용
val lambdaFun3: (Int) -> Int = { x -> x + 10}
val lambdaFun4: (Int) -> Int = { it + 10 }

fun main() {
    funcVal1(10)
    println(funcVal1(10))
    funcVal2()

    println(sum1(10, 20))

    run {
        println("Hello")
    }

    // 객체 타입의 매개변수
    class User(val name: String, val age: Int)

    val lambda1: (User) -> Int = { user: User -> user.age }
    println("lambda1 return : ${lambda1(User("Park", 35))}")

    // it 활용
    val lambda2: (User) -> Int = { it.age }
    println("lambda2 return : ${lambda2(User("Park", 35))}")

    // 멤버 참조
    val lambda3: (User) -> Int = User::age
    println("lambda3 return : ${lambda3(User("Park", 35))}")
}