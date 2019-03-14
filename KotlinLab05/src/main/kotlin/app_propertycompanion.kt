package sixteen_two

class Test {
    val classData: Int = 0
}

//val Test.extensionData: Int = 10 // 에러

// get() 함수로만 확장 가능
val Test.extensionData: Int
get() = 10

class Test2 {
    companion object {
        val data1: Int = 10
        fun myFun1() {
            println("companion object myFun1()...")
        }
    }
}

val Test2.Companion.data2: Int
get() = 20

fun Test2.Companion.myFun2() {
    println("extension myFun2()...")
}

fun main() {
    val obj = Test()
    println("classData : ${obj.classData} ... extensionData : ${obj.extensionData}")

    println("data1 : ${Test2.data1} ... data2 : ${Test2.data2}")
    Test2.myFun1()
    Test2.myFun2()
}