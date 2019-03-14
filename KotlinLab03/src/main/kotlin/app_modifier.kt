// 최상위 구성요소의 접근 제한자
val myData1: Int = 10

private val myData2: String = "Hello"

class MyClass6() {

}

private class MyClass7() {

}

fun myFun1() {
    println("myFun1() call...")
}

private fun myFun2() {
    println("myFun2() call...")
}

fun main() {
    // 최상위 구성요소에 접근
    println("$myData1 ..")
    println("$myData2 ..")
    val obj1 = MyClass6()
    val obj2 = MyClass7()
    myFun1()
    myFun2()
}