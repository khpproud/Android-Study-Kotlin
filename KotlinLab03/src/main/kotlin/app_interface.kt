package ten_two

interface MyInterface {
    var data1: String
    fun myFun1() {

    }

    fun myFun2()
}

class MyClass: MyInterface {
    override var data1: String = "Hello"
    override fun myFun2() {
    }

}

fun main() {
    // val obj = MyInterface() // 에러
    val obj1 = MyClass()
    obj1.myFun1()
    obj1.myFun2()
}