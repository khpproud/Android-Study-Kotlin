package six.one

// 제네릭의 공변(co-variant), 반변(contra-variant), 불변(invariant) 예

// 공변
class Box<T>
fun sum(list: Box<out Number>) { }

// 반변
fun sum2(list: Box<in Number>) { }

fun main() {
    // 공변
    //sum(Box<Any>())               // 에러 Number와 그 하위 형식 만 가능
    sum(Box<Number>())
    sum(Box<Int>())

    // 반변
    sum2(Box<Any>())
    sum2(Box<Number>())
    //sum2(Box<Int>())              // 에러 Number와 그 상위 형식 만 가능
}

