package eleven_five_three

class NormalClass {
    fun myFun() {
    }
}

object ObjectClass {
    fun myFun() {
    }
}

// Nested 클래스로서 object 클래스
class Outer {
    object NestedClass {
        val no: Int = 0
        fun myFun() {
        }
    }
}

// companion 이용
class Outer2 {
    companion object NestedClass {
        val no: Int = 0
        fun myFun() {
        }
    }

    fun myFun() {
        no
        myFun()
    }
}

fun main() {
    val obj1: NormalClass = NormalClass()
    val obj2: NormalClass = NormalClass()
    obj1.myFun()

    //val obj3: ObjectClass = ObjectClass()   // 에러

    ObjectClass.myFun()

    val obj3 = Outer()
    //obj3.NestedClass.no     // 에러

    Outer.NestedClass.no
    Outer.NestedClass.myFun()

    Outer2.NestedClass.no
    Outer2.NestedClass.myFun()

    Outer2.no
    Outer2.myFun()
}