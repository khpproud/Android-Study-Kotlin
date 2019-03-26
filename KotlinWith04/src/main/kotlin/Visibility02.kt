package four

// 최상위 요소(멤버) 안에 선언되는 가시성 한정자 예
class Person2 {
    public val name: String = "Igor"
    protected var age: Int = 23
    internal fun learn() {}
    private fun speak() {}
}

fun main() {
    val person = Person2()
    println(person.name)                // 클라이언트는 Person2 인스턴스와 name 속성에 접근 가능
    //person.speak()                    // 에러, speak 메소드는 Person2 클래스에서만 접근 가능
    person.learn()                      // Person2 클래스 인스턴스에 접근 할 수 있는 모듈 내의 클라이언트는 멤버 접근 가능
    //person.age                        // 에러, age 속성은 Person2 클래스와 해당 하위 클래스 내에서만 접근 가능
}

open class Person3 {
    public val name: String = "Igor"
    protected var age: Int = 23
    internal fun learn() {}
    private fun speak() {}
}

class Student(): Person3() {
    fun doSth() {
        println(name)
        learn()
        print(age)                      // 상속 관계에서는 protected 멤버 접근 가능
        //speak()                       // 에러, private 한정자는 접근 불가능
    }
}