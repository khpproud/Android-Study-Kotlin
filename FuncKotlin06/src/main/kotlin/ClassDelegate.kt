package chap06

// 클래스 위임의 예

interface Person {
    fun printName()
}

class PersonImpl(private val name: String): Person {
    override fun printName() {
        println(name)
    }
}

// User가 Person을 확장하며 제공된 person 인스턴스로 부터 Person의 동작을 복사
class User(private val person: Person): Person by person {
    override fun printName() {
        println("이름 출력:")
        person.printName()
    }
}

fun main() {
    val person = PersonImpl("Kim")
    person.printName()
    println()
    // person 멤버 변수로 User 의 인스턴스를 생성
    val user = User(person)
    // 일반속성과 마찬가지로 person의 속성과 함수에 접근 가능
    user.printName()
}