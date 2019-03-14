package eleven_four_one

// Outer 클래스의 멤버 접근 에러
class Outer {
    var no: Int = 10
    fun outerFun() {
        println("outerFun()...")
    }
    class Nested {
        val name: String = "Park"
        fun myFun() {
            println("Nested... myFun...")
            //no = 20     // 에러 - 외부 클래스의 멤버 접근 불가
            //outerFun()  // 에러
        }
    }
}

// inner 이용 - 에러
class Outer2 {
    private var no: Int = 10
    fun outerFun() {
        println("outerFun()...")
    }
    inner class Nested {
        val name: String = "Kim"
        fun myFun() {
            println("Nested... myFun...")
            no = 20
            outerFun()
        }
    }

}

// inner 클래스의 객체 생성
class Outer3 {
    private var no: Int = 10
    fun outerFun() {
        println("outerFun()...")
    }
    inner class Nested {
        val name: String = "Kim"
        fun myFun() {
            println("Nested... myFun...")
            no = 20
            outerFun()
        }
    }

    fun createNested(): Nested {
        return Nested()
    }
}

fun main() {
    val obj: Outer.Nested = Outer.Nested()
    println(obj.name)
    obj.myFun()

    //val obj2: Outer2.Nested = Outer2.Nested() // 에러

    val obj3: Outer3.Nested = Outer3().Nested()
    val obj4: Outer3.Nested = Outer3().createNested()
}