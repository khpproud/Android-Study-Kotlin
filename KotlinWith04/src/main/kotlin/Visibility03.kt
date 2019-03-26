package four

// 멤버를 재정의하면서 한정자를 재정의하는 예
open class Person4 {
    protected open fun speak() { }
}

class Student2() : Person4() {
    public override fun speak() { }
}

fun main() {
    val person = Person4()
    //person.speak()                    // protected로 접근 할 수 없음
    val student = Student2()
    student.speak()                     // 가시성을 public 으로 변경했으므로 접근 가능
}