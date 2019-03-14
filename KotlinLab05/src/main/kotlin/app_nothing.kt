package seventeen_six_two

// 함수의 반환 타입으로 Nothing 사용
fun myFun(): Nothing {
    while(true) {
        // ...
    }
}

fun myFun2(): Nothing? {
    return null
}

fun myFun3(): Nothing {
    throw Exception()
}

// Nothing 타입의 대입
val myVal1: Nothing? = null

// Nothing이 모든 타입의 가장 하위이며 모든 타입에 대입 가능
val myVal2: Int? = myVal1
val myVal3: String? = myVal1

// 제네릭에서 Nothing 타입 사용 - contravariance in 사용
class MyClass<T>

fun someFun(arg: MyClass<in Nothing>) { }

fun main() {
    // 어떤 제네릭 타입도 대입 가능
    someFun(MyClass<Int>())
    someFun(MyClass<String>())
}