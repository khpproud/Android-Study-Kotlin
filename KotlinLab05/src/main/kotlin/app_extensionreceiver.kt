package sixteen_three

// 디스패치 리시버 내에서 익스텐션 리시버 확장
class ExtensionClass {
    fun some1() {
        println("ExtensionClass some1()")
    }
}

class DispatchClass {
    fun dispatchFun() {
        println("DispatchClass dispatchFun()")
    }

    fun ExtensionClass.some2() {
        some1()
        dispatchFun()
    }

    fun test() {
        val obj = ExtensionClass()
        obj.some1()
        obj.some2()
    }
}

fun main() {
    val obj = ExtensionClass()
    obj.some1()
    // obj.some2()  // 에러 디스패치 내부에서만 사용 가능

    val obj2 = DispatchClass()
    obj2.test()
}