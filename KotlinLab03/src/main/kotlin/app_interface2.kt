package ten_two_one

interface MyInterface1 {
    fun myFun1()
}

interface MyInterface2 {
    fun myFun2()
}

interface MyInterface3: MyInterface1, MyInterface2 {
    fun myFun3()
}

class MyClass1: MyInterface3 {
    override fun myFun1() {
    }

    override fun myFun2() {
    }

    override fun myFun3() {
    }
}

open class Super {

}

class Sub: Super(), MyInterface1, MyInterface2 {
    override fun myFun1() {
    }

    override fun myFun2() {
    }
}