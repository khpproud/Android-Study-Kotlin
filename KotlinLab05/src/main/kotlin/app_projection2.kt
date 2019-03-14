package seventeen_four_four

// <*> 과 <out Any?>
fun some(list: MutableList<Int>) {
    list.add(10)
}

fun some2(list: MutableList<out Any?>) {
    //list.add(10)      // 에러 제네릭 타입 매개변수 이용 불가
}

fun some3(list: MutableList<*>) {
    //list.add(10)      // 에러 위와 같음
}

fun main() {
    val list = mutableListOf<Int>(10, 20, 30)
    some2(list)
    some3(list)
}