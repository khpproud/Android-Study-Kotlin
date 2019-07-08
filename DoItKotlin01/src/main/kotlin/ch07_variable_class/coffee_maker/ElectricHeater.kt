package ch07_variable_class.coffee_maker

// 전기 히터 클래스
class ElectricHeater(var heating: Boolean = false) : Heater {
    override fun on() {
        println("[ElectricHEater] heating...")
        heating = true
    }

    override fun off() {
        heating = false
    }

    override fun isHot(): Boolean  = heating
}