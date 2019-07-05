package ch05_class

import ch03_function.b

// 자동차와 도둑 : 가시성 지시자의 사용 예
open class Car protected constructor(_year: Int, _model: String, _power: String, _wheel: String) {
    private var year: Int = _year
    var model: String = _model
    protected open var power: String = _power
    internal var wheel: String = _wheel

    protected fun start(key: Boolean) {
        if (key) println("Start the Engine!")
    }

    class Driver(_name: String, _license: String) {
        private var name: String = _name
        var license: String = _license
        internal fun drive() = println("[Driver] Driving() - $name")
    }
}

class Benz(_year: Int, _model: String, _power: String, _wheel: String, var name: String, private var key: Boolean)
    : Car(_year, _model, _power, _wheel) {
    override var power: String ="50hp"
    val driver = Driver(name, "first class")

    constructor(_name: String, _key: Boolean) : this(2019, "basic", "100hp", "normal",
        _name, _key) {
        name = _name
        key = _key
    }

    fun access(password: String) {
        if (password == "goBenz") {
            println("-----[Benz] access()-----")
            // super.year                               // private 접근 불가
            println("super.model = ${super.model}")     // public
            println("super.power = ${super.power}")     // protected
            println("super.wheel = ${super.wheel}")     // internal
            super.start(key)

            // driver.name                              // private 접근 불가
            println("Driver().license = ${driver.license}")
            driver.drive()                              // internal
        } else {
            println("You're a burglar")
        }
    }
}

class Burglar() {
    fun steal(anycar: Any) {
        if (anycar is Benz) {                           // 인자가 Benz 의 객체일 경우
            println("----[Burglar] steal()-----")
            // println(anycar.power)                    // protected 접근 불가
            // println(anycar.year)                     // private 접근 불가
            println("anycar.name = ${anycar.name}")     // public 접근
            println("anycar.wheel = ${anycar.wheel}")   // internal 접근
            println("anycar.model = ${anycar.model}")   // public 접근

            println(anycar.driver.license)              // public 접근
            anycar.driver.drive()                       // internal 접근
            // println(Car.start())                     // protected 접근 불가
            anycar.access("don't know")
        } else {
            println("Nothing ti steal")
        }
    }
}

fun main() {
    // val car = Car()                                  // protected 생성 불가
    val benz = Benz("Koo", true)
    benz.access("goBenz")

    val burglar = Burglar()
    burglar.steal(benz)
}