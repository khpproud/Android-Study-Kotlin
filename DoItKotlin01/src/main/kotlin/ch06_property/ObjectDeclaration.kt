package ch06_property

// object 선언과 컴패니언 객체 비교
// object 롤 만듬
object OCustomer {
    var name = "Koo"
    fun greeting() = println("Hello $name")
    val HOBBY = Hobby("BasketBall")
    init {
        println("Init!!")
    }
}

// Companion 객체
class CCustomer {
    companion object {
        const val HELLO = "Hello"
        var name = "Kim"
        @JvmField val hobby = Hobby("FootBall")
        @JvmStatic fun greeting() = println("Hello $name")
    }
}

class Hobby(val name: String)

fun main() {
    OCustomer.greeting()
    OCustomer.name = "Park"
    println("name = ${OCustomer.name}")
    println("hobby = ${OCustomer.HOBBY.name}")

    CCustomer.greeting()
    println("name = ${CCustomer.name}, HELLO = ${CCustomer.HELLO}")
    println("hobby = ${CCustomer.hobby.name}")
}