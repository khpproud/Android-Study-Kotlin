package nineteen_three

// 타입 에일리어스
typealias MyInt = Int
typealias MList<T> = MutableList<T>
typealias MC = MyClass
typealias MI = MyInterface

interface MyInterface
class MyClass: MI

// 함수 타입 정의
typealias MyType = (Int) -> Boolean
val myFun: MyType = { it > 10 }

// inner 클래스 타입 재정의
typealias MySub = Super.Sub
class Super {
    inner class Sub

    fun getSubInstance(): MySub {
        return Sub()
    }
}

fun main() {
    val no: MyInt = 10
    val list: MList<String> = mutableListOf("good", "better", "best")
    val obj: MC = MC()
}