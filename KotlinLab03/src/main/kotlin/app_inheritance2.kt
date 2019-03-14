fun smartCast(data: Any): Int {
    return if (data is Int)
        data * data
    else 0
}

class MyClass4 {
    fun fun1() {
        println("fun1()...")
    }
}

class MyClass5 {
    fun fun2() {
        println("fun2()...")
    }
}

fun smartCast2(obj: Any) {
    if (obj is MyClass4)
        obj.fun1()
    else if (obj is MyClass5)
        obj.fun2()
}

open class Super1 {
    fun superFun() {
        println("superFun()...")
    }
}

class Sub4: Super1() {
    fun subFun4() {
        println("subFun4()...")
    }
}

class Sub5: Super1() {
    fun subFun5() {
        println("subFun5()....")
    }
}

fun main() {
    println("result : ${smartCast(10)}")
    println("result : ${smartCast(10.0)}")

    smartCast2(MyClass4())
    smartCast2(MyClass5())

    // 하위 타입 -> 상위 타입 -> 하위 타입
    val obj3: Super1 = Sub4()
    val obj4: Sub4 = obj3 as Sub4
    obj4.superFun()
    obj4.subFun4()

    // 상위 타입 -> 하위 타입 : 런타임 에러 발생
    //val obj5: Sub4 = Super1() as Sub4
    //obj5.subFun4()

    // 하위 타입 -> 하위 타입 : 런타임 에러 발생
    //val obj6: Sub5 = Sub4() as Sub5

    // null 허용 객체 캐스팅
    val obj7: Super1? = Sub4()
    val obj8: Sub4 = obj7 as Sub4

    // null 허용 캐스팅 에러 : 런타임 에러(null 캐스팅 안됨)
    //val obj9: Super1? = null
    //val obj10: Sub4 = obj9 as Sub4

    // as?에 의한 캐스팅
    val obj11: Super1? = null
    val obj12: Sub4? = obj11 as? Sub4
}