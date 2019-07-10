package ch06_property

class User(_id: Int, _name: String, _age: Int) {
    val id: Int = _id
    var name: String = _name
        set(value) {
            println("The name was changed")
            field = value.toUpperCase()             // 받은 인자를 대문자로 변경해 프로퍼티에 할당
        }
    var age: Int = _age
}

fun main() {
    val user1 = User(1, "Koo", 35)
    user1.name = "Kim"                              // 여기서 이름이 변경될 때 메시지 출력
    println("user.name = ${user1.name}")
}