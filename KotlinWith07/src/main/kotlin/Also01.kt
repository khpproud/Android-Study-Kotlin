package seven.also

// also - apply와 비슷하지만 매개변수 함수가 받는 인수가 리시버가 아닌 매개변수
class Snail {
    var name: String = ""
    var type: String = ""
    fun greet() {
        println("Hello, I am $name")
    }
}

class Forest {
    var members = listOf<Snail>()
    fun Snail.reproduce(): Snail = Snail().also {
        it.name = name
        it.type = type
        members += it
    }
}

