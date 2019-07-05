package ch05_class

// 구성 관계 나타내기
class Car2(val name: String, val power: String) {
    private var engine = Engine(power)                  // Engine 클래스는 Car 에 의존적

    fun startEngine() = engine.start()
    fun stopEngine() = engine.stop()
}

class Engine(power: String) {
    fun start() = println("Engine has been started!")
    fun stop() = println("Engine has been stopped!")
}

fun main() {
    val car = Car2("Benz", "100hp")
    car.startEngine()
    car.stopEngine()
}