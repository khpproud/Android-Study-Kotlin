package seventeen_four_five

// <in T>로 선언한 것을 <*> 로 이용
class MyClass<in T> {
    fun myFun(a: T) {
    }
    fun myFun2() { }
}

fun some(arg: MyClass<*>) {
    //arg.myFun(10)     // 에러 대입되는 타입을 모르므로 <T> 타입의 매개변수가 있는 함수 호출 불가능
    arg.myFun2()
}

fun some2(arg: MyClass<in Any?>) {
    arg.myFun(10)
    arg.myFun2()
}

fun some3(arg: MyClass<in Nothing>) {
    //arg.myFun(10)     // 에러
    arg.myFun2()
}