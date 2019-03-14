package thirteen_two_one

// 고차 함수 이용
inline fun hoFunTest(argFun: (x1: Int, x2: Int) -> Int) {
    println(argFun(10, 20))
}

// inline 적용 함수
inline fun inlineFunTest(argFun1: (x: Int) -> Int, argFun2: (x: Int) -> Int) {
    println(argFun1(10))
    println(argFun2(10))
}

// noinline 이용
inline fun inlineFunTest2(argFun1: (x: Int) -> Int, noinline argFun2: (x: Int) -> Int) {
    println(argFun1(10))
    println(argFun2(10))
}

// 람다 함수에서 return 사용 가능(inline 선언시)
inline fun inlineTest2(argFun: (x: Int, y: Int) -> Int): Int {
    return argFun(10, 0)
}

fun callFun() {
    println("callFun... top")
    val result = inlineTest2 { x, y ->
        if (y <= 0)
            return
        x / y
    }
    println(result)
    println("callFun... bottom")
}

// 매개변수를 다른 객체에 대입해서 이용
open class TestClass {
    open fun some() {}
}

// crossinline 이용
inline fun inlineTest3(crossinline argFun: () -> Unit) {
    val obj = object : TestClass() {
        override fun some() = argFun()
    }
}

fun crossInlineTest() {
    println("CrossInlineTest...")
    inlineTest3 {
        // crossinline은 람다함수에 return을 사용하지 못하게 하는 예약어
        //return
    }
}

// array 데이터 출력 - return 사용
val array = arrayOf(1, -1, 2)
fun arrayLoop() {
    println("loop top...")
    array.forEach {
        if (it < 0)
            return
        println(it)
    }
    println("loop bottom...")
}

// array 데이터 출력 - 라벨 이용
fun arrayLoopLabel() {
    println("loop top...")
    array.forEach loop@{
        if (it < 0)
            return@loop
        println(it)
    }
    println("loop bottom...")
}

// 자동 추가 라벨 이용
fun arrayLoop2() {
    println("loop top...")
    array.forEach {
        if (it < 0)
            return@forEach
        println(it)
    }
    println("loop bottom...")
}

// 개발자 고차 함수에서의 return
inline fun hoTest(argFun: (String) -> Unit) {
    argFun("Hello")
    argFun("Kim")
    argFun("World")
}

fun labelTest() {
    println("test top...")
    hoTest {
        if (it.length < 4)
            return@hoTest
        println(it)
    }
    println("test bottom...")
}

// 익명 함수 이용 return
fun arrayLoop3() {
    println("loop before...")
    array.forEach(fun(value: Int) {
        if (value < 0)
            return
        println(value)
    })
    println("loop after...")
}

fun main() {
    val result = hoFunTest { x1, x2 -> x1 + x2 }

    inlineFunTest({it * 10}, {it * 20})
    inlineFunTest2({it * 10}, {it * 20})

    callFun()

    crossInlineTest()

    arrayLoop()
    arrayLoopLabel()
    arrayLoop2()

    labelTest()

    arrayLoop3()
}