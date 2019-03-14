package seventeen_six_one

// 타입으로서 Unit
fun myFun1() { }
fun myFun2(): Unit { }

fun myFun3(): Unit {
    return Unit
}

val myVal: Unit = Unit

// 제네릭 타입으로 Unit 타입 활용
interface MyInterface<T> {
    fun myFun(): T
}

class MyClass: MyInterface<String> {
    override fun myFun(): String {
        return "Hello"
    }
}

// 반환 타입을 명시하지 않아도 됨
class MyClass2: MyInterface<Unit> {
    override fun myFun() {
    }
}

fun main() {
    println(myFun1())
}