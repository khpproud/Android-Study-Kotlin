package ten_one

abstract class Super {
    val data1: Int = 10
    abstract val data2: Int

    fun myFun1() {}

    abstract fun myFun2()
}

class Sub: Super() {
    override val data2: Int = 10
    override fun myFun2() {
    }
}

fun main() {
    //val obj1 = Super()
    val obj2 = Sub()
}