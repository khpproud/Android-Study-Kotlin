package seventeen_four_two

// Array 클래스 copy() 함수 작성
fun copy(from: Array<Int>, to: Array<Int>) {
    for (i in from.indices) {
        to[i] = from[i]
    }
}

// Array 클래스 copy() 함수 작성, Any타입으로 선언
fun copy2(from: Array<Any>, to: Array<Any>) {
    for (i in from.indices) {
        to[i] = from[i]
    }
}

// 이용 측 Variance out
fun copy3(from: Array<out Any>, to: Array<Any>) {
    for (i in from.indices) {
        to[i] = from[i]
    }
}

fun main() {
    val array1: Array<Int> = arrayOf(1, 2, 3)
    val array2 = Array(3) { 0 }
    array2.forEach { println(it) }
    copy(array1, array2)
    array2.forEach { println(it) }

    val array3: Array<Int> = arrayOf(1, 2, 3)
    val array4 = Array(3) { 0 }
    //copy2(array3, array4)     // 에러 invariance Array<Int> 는 Array<Any>의 하위가 아님
    array4.forEach { println(it) }

    val array5: Array<Int> = arrayOf(1, 2, 3)
    val array6 = Array<Any>(3) { 0 }
    copy3(array5, array6)
    array6.forEach { println(it) }
}