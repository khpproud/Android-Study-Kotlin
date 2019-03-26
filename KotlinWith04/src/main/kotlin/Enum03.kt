package four

// 각 열거형에 맞춤 속성 추가 예
enum class Temperature { COLD, NEUTRAL, WARM }

enum class Color3(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0) {
        override val temperature = Temperature.WARM
    },
    ORANGE(255, 165, 0) {
        override val temperature = Temperature.WARM
    },
    BLUE(0, 0, 255) {
        override val temperature = Temperature.COLD
    },
    GRAY(49, 79, 79) {
        override val temperature = Temperature.NEUTRAL
    },
    VIOLET(238, 130, 238) {
        override val temperature = Temperature.COLD
    };

    init {
        require(r in 0.. 255)
        require(g in 0.. 255)
        require(b in 0.. 255)
    }

    fun rgb() = (r * 256 + g) * 256 + b

    abstract val temperature: Temperature
}

fun main() {
    println(Color3.BLUE.temperature)
    println(Color3.ORANGE.temperature)
    println(Color3.GRAY.temperature)
}