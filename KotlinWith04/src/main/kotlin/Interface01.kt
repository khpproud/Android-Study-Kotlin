package four

// 기본 속성을 갖는 인터페이스
interface EmailProvider {
    fun validateEmail(): Boolean = nickname.isNotEmpty()

    val email: String

    val nickname: String
    get() = email.substringBefore("@")
}

class User(override val email: String): EmailProvider

interface A {
    fun foo() = println("A")
}

interface B {
    fun foo() = println("B")
}

class Item: A, B {
    override fun foo() {
        val a = super<A>.foo()
        val b = super<B>.foo()
        println("Item $a $b")
    }
}

fun main() {
    val user = User("great@test.com")
    println(user.nickname)
    println(user.validateEmail())

    val item = Item()
    item.foo()
}