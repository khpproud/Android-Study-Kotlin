package seventeen_one

// 제네릭 선언 및 이용
class MyClass<T> {
    var info: T? = null
}

// 타입 유추에 의한 제네릭 이용
class MyClass2<T>(no: T) {
    var info: T? = null
}

// 함수의 형식 타입
class MyClass3<T, U> {
    var info: T? = null
    var data: U? = null

    fun myFun(arg: T): U? {
        return data
    }
}

// 최상위 레벨 함수에서의 제네릭
fun <T> someFun(arg: T): T? {
    return null
}

fun main() {
    val obj1 = MyClass<String>()
    obj1.info = "hello"

    val obj2 = MyClass<Int>()
    obj2.info = 10

    println("obj1 : ${obj1.info} ... obj2 : ${obj2.info}")

    // 타입 유추
    val obj3 = MyClass2(10)
    obj3.info = 20

    val obj4 = MyClass2("hello")
    obj4.info = "world"
}