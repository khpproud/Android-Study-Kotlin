package chp07_variable_class

// 인터페이스에서 위임 사용 예
interface Nameable {
    var name: String
}

class StaffName : Nameable {
    override var name: String = "Sean"
}

class Work : Runnable {
    override fun run() {
        println("Work...")
    }
}

// 각 매개변수에 해당 인터페이스를 위임
class Person(name: Nameable, work: Runnable) : Nameable by name, Runnable by work

fun main() {
    val person = Person(StaffName(), Work())            // 생성자를 사용해 객체 바로 전달
    println(person.name)
    person.run()
}