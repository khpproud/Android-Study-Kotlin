package chap02

// also 함수를 이용한 swap 함수 만들기
fun main() {
    // 보통의 자바에서 swap()
    var a = 1
    var b = 2

    run {
        val temp = a
        a = b
        b = temp
    }

    println(a)
    println(b)

    //also를 사용
    a = 1
    b = 2
    a = b.also {
        b = a
        println("it = $it, b = $b, a = $a")
    }
    println(a)
    println(b)

    // also 와 apply 비교
    // also => (T) -> Unit (it 으로 참조)
    var result = Dog(12).also { it.age = 15 }
    // apply => T.() -> Unit (묵시적 this 참조)
    result = Dog(12).apply { age = 15 }
}

data class Dog(var age: Int)