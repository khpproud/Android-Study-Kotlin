package ch07_variable_class.coffee_maker

// 열 사이펀 클래스
class Thermosiphon(heater: Heater) : Pump, Heater by heater {
    override fun pump() {
        if (isHot()) {
            println("[Thermosiphon] pumping...")
        }
    }
}