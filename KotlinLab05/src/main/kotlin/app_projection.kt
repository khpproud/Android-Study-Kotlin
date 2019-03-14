package seventeen_four_three

// 스타 프로젝션의 이용 위치
//class MyClass<*>  // 에러 선언시에는 이용할 수 없음

class MyClass2<T>

fun myFun(arg: MyClass2<*>) { }     // 이용 시에만 프로젝션 사용 가능

fun main() {
    // <Any?> 와 <*>의 이용
    val list: MutableList<Any?> = mutableListOf(10, 10.0, "Park")
    list.forEach { println(it) }
    val list2: MutableList<*> = mutableListOf(10, 10.0, "Park")
    list2.forEach { println(it) }

    // <Any?> 와 <*>의 차이
    //val list3: MutableList<Any?> = mutableListOf<Any>(10, 10.0, "Park")   // invariance 이므로 에러
    //list3.forEach { println(it) }

    val list4: MutableList<*> = mutableListOf<Any>(10, 10.0, "Park")    // 프로젝션 사용
    list4.forEach { println(it) }
}