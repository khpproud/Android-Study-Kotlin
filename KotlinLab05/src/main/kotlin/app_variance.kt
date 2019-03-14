package seventeen_four_one

class MyClass<T>(val data: T) {
    fun myFun(): T {
        return data
    }
    fun myFun2(arg: T) {

    }
    fun myFun3(arg: T): T {
        return data
    }
}

// invariance variance를 허용하지 않음
fun some(arg: MyClass<Number>) {
    arg.myFun()
    arg.myFun2(10)
    arg.myFun3(10)
}

// 이용 측 variance, in 이용
fun some2(arg: MyClass<in Int>) {
    arg.myFun()
    arg.myFun2(10)
    arg.myFun3(10)
}

// 이용 측 variance, ouy 이용
fun some3(arg: MyClass<out Number>) {
    arg.myFun()
    //arg.myFun2(10)    // 에러 매개변수를 제네릭으로 선언한 함수는 이용 X
    //arg.myFun3(10)
}

fun main() {
    some(MyClass<Number>(10))
    //some(MyClass<Int>(10))    // 에러 invariance

    some2(MyClass<Int>(10))
    some2(MyClass<Number>(10))  // in 상위 -> 하위

    some3(MyClass<Number>(10))
    some3(MyClass<Int>(10))     // out 하위 -> 상위
}