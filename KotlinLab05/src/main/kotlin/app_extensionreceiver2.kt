package sixteen_three_two

// 익스텐션 리시버와 디스패치 리시버의 함수명 중복
class ExtensionClass {
    fun myFun() {
        println("ExtensionClass myFun()...")
    }
}

class DispatchClass {
    fun myFun() {
        println("DispatchClass myFun()...")
    }

    fun ExtensionClass.some() {
        myFun()
        this.myFun()
        this@DispatchClass.myFun()
    }

    fun test() {
        val obj = ExtensionClass()
        obj.some()
    }
}

fun main() {
    val obj = DispatchClass()
    obj.test()
}