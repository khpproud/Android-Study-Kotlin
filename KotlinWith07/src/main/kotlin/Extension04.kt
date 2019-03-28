package seven.four

// 멤버 확장
fun List<String>.dropOneEvery(i: Int) = filterIndexed { index, _ -> index % i == (i - 1) }

class A {
    fun boo() {
        println("boo")
    }

    fun Int.foo() {
        boo()
    }
}