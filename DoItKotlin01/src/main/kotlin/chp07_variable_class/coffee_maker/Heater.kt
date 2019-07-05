package chp07_variable_class.coffee_maker

// Heater 인터페이스
interface Heater {
    fun on()
    fun off()
    fun isHot(): Boolean
}