package seventeen_two

// 타입 제약
class MathUtil<T: Number> {
    fun plus(arg1: T, arg2: T): Double {
        return arg1.toDouble() + arg2.toDouble()
    }
}

// 여러개의 제약
interface MyInterface1
interface MyInterface2

class MyHandler1: MyInterface1, MyInterface2
class MyHandler2: MyInterface1

class MyClass<T> where T: MyInterface1, T: MyInterface2 {
}

// Null 허용 형식 타입
class MyClass2<T> {
    fun myFun(arg1: T, arg2: T) {
        println(arg1?.equals(arg2))
    }
}

// Null 불허 형식 타입
class MyClass3<T: Any> {
    fun myFun(arg1: T, arg2: T) {
        println(arg1.equals(arg2))
    }
}

fun main() {
    val obj = MathUtil<Int>()
    obj.plus(10, 20)

    val obj2 = MathUtil<Double>()

    // val obj3 = MathUtil<String> // 에러 Number의 하위 클래스만 가능

    val obj4 = MyClass<MyHandler1>()
    // val obj5 = MyClass<MyHandler2>() // MyInterface1, MyInterface2 모두 구현한 클래스만 가능

    val obj6 = MyClass2<String>()
    obj6.myFun("hello", "hello")

    val obj7 = MyClass2<Int?>()
    obj7.myFun(null, 10)

    val obj8 = MyClass3<String>()
    obj8.myFun("world", "world")

    //val obj9 = MyClass3<Int?>()   // 에러 Null이 허용되지 않음
    //obj9.myFun(null, 10)
}