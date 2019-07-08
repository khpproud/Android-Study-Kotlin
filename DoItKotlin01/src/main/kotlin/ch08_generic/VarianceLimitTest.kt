package ch08_generic

// 자료형을 제한하는 제네릭 클래스
open class Animal(val size: Int) {
    fun feed() = println("Feeding...")
}

class Cat(val jump: Int) : Animal(50)
class Spider(val posion: Boolean) : Animal(1)

// 형식 매개변수를 Animal 로 제한
class Box<out T: Animal>(val element: T) {          // 주 생성자는 val 만 허용
    fun getAnimal(): T = element                    // out 은 반환 자료형에만 사용할 수 있음
//    fun set(new: T) {                               // 에러 T는 in 위치에 사용할 수 없음
//        element = new
//    }
}

fun main() {
    val c1: Cat = Cat(10)
    val s1: Spider = Spider(true)

    var a1: Animal = c1                             // 클래스의 상위 자료형으로 변환하는 것은 아무런 문제 없음
    a1 = s1                                         // a1 은 Spider 의 객체가 됨
    println("a1.size = ${a1.size}")

    val c2: Box<Animal> = Box<Cat>(Cat(10))   // 공변성 - Cat 은 Animal 의 하위 자료형
    println("c2.element.sie = ${c2.element.size}")

//    val c3: Box<Cat> = Box<Animal>(10)        // 오류! 반대의 경우는 인스턴스화 되지 않음
//    val c4: Box<Any> = Box<Int>(10)           // 오류! 자료형을 제한하여 Animal과 하위 클래스 이외에는 사용할 수 없음
}