package seventeen_three

// out을 이용한 형식 타입 선언
open class Super

class Sub: Super()

class MyClass<out T>

// out 어노테이션 사용 규칙
class MyClass2<out T>(val data: T) {
    val myVal: T? = null
    //var myVal2: T? = null     // var 프로퍼티에 선언 불가능


    fun myFun(): T {
        return data
    }

    //fun myFun2(arg: T) { }    // 함수의 매개변수 타입으로 선언 불가능
}

fun main() {
    val obj = MyClass<Sub>()
    val obj2: MyClass<Super> = obj

    val obj3 = MyClass<Super>()
    //val obj4: MyClass<Sub> = obj3     // 에러 out은 하위 타입을 상위 타입으로만 사용 가능

    // out을 이용한 List 선언
    val mutableList: MutableList<Int> = mutableListOf(10, 20)
    // val mutableList2: MutableList<Number> = mutableList      // 에러

    val immutableList: List<Int> = listOf(10, 20)
    val immutableList2: List<Number> = immutableList
}