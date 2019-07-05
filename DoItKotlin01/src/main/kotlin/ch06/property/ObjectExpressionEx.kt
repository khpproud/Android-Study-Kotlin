package ch06.property

// object 표현식 사용 예
open class Superman {
    fun work() = println("Taking photos")
    fun talk() = println("Talking with people")
    open fun fly() = println("Flying in the air")
}

fun main() {
    val pretendedMan = object : Superman() {            // object 표현식으로 fly() 구현의 재정의
        override fun fly() = println("I'm not a real Superman. I can't fly!")
    }

    pretendedMan.talk()
    pretendedMan.work()
    pretendedMan.fly()
}