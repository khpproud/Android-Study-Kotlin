package chap03

// 생성자 초기화 예
// 클래스 선언
class Student(var rollNumber: Int, var name: String)

// 생성자에 기본값을 설정
class Student1 constructor(var rollNumber: Int, var name: String = "Park")

// this 키워드를 사용하여 또 다른 생성자를 같은 클래스에서 사용 가능 (보조 생성자에서 주 생성자 호출)
open class Person(val name: String) {
    constructor(name: String, lastName: String): this(name) {
        // 초기화를 위한 코드
    }
}

// init을 통한 초기화
class Student2(var rollNumber: Int, var name: String) {
    init {
        // 초기화를 위한 코드
    }
}

// 상송할 때 부모클래스 또한 코기화 해줘야 함
class Student3 constructor(var rollNumber: Int, name: String): Person(name)

// 기본 생성자가 없는 경우
class Student4: Person {
    constructor(name: String): super(name)
    constructor(name: String, rollNumber: Int): this(name)

}

fun main() {
    // 객체 생성
    var studentA = Student(1, "Kim")

    println("Roll Number : ${studentA.rollNumber}, Name : ${studentA.name}")

    var studentB = Student1(24)
    var studentC = Student1(22, "Lee")
}