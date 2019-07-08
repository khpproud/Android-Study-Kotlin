package ch07_variable_class.coffee_maker

// 드립 커피 모듈
class MyDripCoffeeModule : CoffeeModule {
    companion object {
        val electricHeater: ElectricHeater by lazy {
            ElectricHeater()
        }
    }

    // 임시적인 private 프로퍼티
    private val _thermosiphon : Thermosiphon by lazy {
        Thermosiphon(electricHeater)
    }

    override fun getThermosiphon(): Thermosiphon =_thermosiphon
}