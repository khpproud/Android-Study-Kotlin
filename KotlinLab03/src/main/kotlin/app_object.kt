package eleven_five

// object 로 익명클래스를 작성
val obj1 = object {
    var no1: Int = 10
    fun myFun() {

    }
}

class Outer {
    val obj2 = object {
        var no2: Int = 0
        fun myFun() {

        }
    }
}

// object 이용 시 멤버 접근 문제
class Outer2 {
    private var no: Int = 10

    val myInner = object {
        val name: String = "Park"
        fun innerFun() {
            println("innerFun()...")
            no++
        }
    }

    fun outerFun() {
        //myInner.name        // 에러
        //myInner.innerFun()  // 에러
    }
}

// object 멤버 이용
class Outer3 {
    private var no: Int = 0

    private val myInner = object {
        val name: String = "Park"
        fun innerFun() {
            println("innerFun()...")
            no++
        }
    }

    fun outerFun() {
        myInner.name
        myInner.innerFun()
    }
}

// 타입 선언 object 클래스
interface SomeInterface {
    fun interfaceFun()
}

open class SomeClass {
    fun someClassFun() {
        println("someClassFun()...")
    }
}

class Outer4 {
    val myInner: SomeClass = object: SomeClass(), SomeInterface {
        override fun interfaceFun() {
            println("interfaceFun()...")
        }
    }
}

fun main() {
    val obj = Outer2()
    //obj.myInner.name            // 에러
    //obj.myInner.innerFun()      // 에러

    val obj2 = Outer4()
    obj2.myInner.someClassFun()
    (obj2.myInner as SomeInterface).interfaceFun()
}