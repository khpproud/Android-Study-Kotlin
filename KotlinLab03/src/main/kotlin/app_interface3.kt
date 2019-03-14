package ten_two_two

interface MyInterface1 {
    fun myInterfaceFun()
}

open class Super1 {
    fun mySuperFun() {
        println("mySuperFun()...")
    }
}

class Sub1: Super1(), MyInterface1 {
    override fun myInterfaceFun() {
        println("myInterfaceFun call...")
    }
}

interface MyInterface2 {
    var prop1: Int //abstract
    //val prop2: String = "Park" // 에러

    // field 사용할 수 없음
    //val prop2: String // 에러
    //get() = field

    // filed 사용할 수 없음
    //var prop3: String // 에러
    //get() = field

    // 추상 프로퍼티가 아니라면 val은 get() 함수를 꼭 선언
    val prop4: String
    get() = "Park"

    // 추상 프로파티가 아니라면 var은 get(), set() 함수 꼭 선언
    var prop5: String
    get() = "Park"
    set(value) {
    }
}

// 프로퍼티의 get(), set()
interface MyInterface3 {
    var data1: Int

    var data2: Int
    get() = 0
    set(value) {
        if (value > 0)
            calData(value)
    }

    val data3: Boolean
    get() {
        return data1 > 0
    }

    private fun calData(arg: Int) {
        data1 = arg * arg
    }
}

class MyClass1: MyInterface3 {
    override var data1: Int = 0
}

fun main() {
    val obj1: Sub1 = Sub1()
    val obj2: Super1 = Sub1()
    val obj3: MyInterface1 = Sub1()

    // Sub1 타입 객체 이용
    obj1.mySuperFun()
    obj1.myInterfaceFun()

    // Super1 타입 객체 이용
    obj2.mySuperFun()
    //obj2.myInterfaceFun() // 에러

    // MyInterface1 타입 객테 이용
    //obj3.mySuperFun() // 에러
    obj3.myInterfaceFun()

    val obj = MyClass1()
    println("data1 : ${obj.data1}, data2 : ${obj.data2}, data3 : ${obj.data3}")
    obj.data2 = 5
    println("data1 : ${obj.data1}, data2 : ${obj.data2}, data3 : ${obj.data3}")
}