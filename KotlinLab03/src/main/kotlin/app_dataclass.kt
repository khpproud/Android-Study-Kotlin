package eleven_one

class Product(val name: String, val price: Int)

data class User(val name: String, val age: Int)

// 다른 클래스 객체 간의 equals() 함수
data class User1(val name: String, val age: Int)
data class Person(val name: String, val age: Int)

// 클래스 내에 프로퍼티가 선언된 경우
data class User2(val name: String, val age: Int) {
    var email: String = "b@b.com"
}

fun main() {
    // equals()
    var product = Product("prod1", 100)
    var product1 = Product("prod1", 100)
    println(product.equals(product1))

    var user = User("Kim", 20)
    var user1 = User("Kim", 20)
    println(user.equals(user1))

    val user2 = User1("Lee", 25)
    val person = Person("Lee", 25)
    println(user2.equals(person))

    val user3 = User2("Oh", 30)
    val user4 = User2("Oh", 30)
    user4.email = "c@c.com"

    println(user3.equals(user4))
}