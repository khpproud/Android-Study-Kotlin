package seventeen_three_two
// in 어노테이션 규칙

open class Super

class Sub: Super()

class MyClass<in T>() {
    //val myVal: T? = null  // 프로퍼티에 선언 불가능
    //var myVar: T? = null  // 프로퍼티에 선언 불가능

    /*
    fun myFun(): T? {
        return null
    }
    */                      // 함수의 반환타입으로 선언 불가능

    fun myFun(arg: T) {     // 함수의 매개변수 타입으로 선언 가능

    }
}

fun main() {
    val obj = MyClass<Sub>()
    //val obj2: MyClass<Super> = obj    // 하위 제네릭 타입을 상위 타입에 대입 불가능

    val obj3 = MyClass<Super>()
    val obj4: MyClass<Sub> = obj3       // 상위 제네릭 타입을 하위 타입에 대입 가능
}