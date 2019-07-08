package ch07_variable_class

// 이너 클래스의 바깥 클래스 멤버 접근
class Smartphone(val model: String) {
    private val cpu = "Exynos"

    inner class ExternalStorage(val size: Int) {
        fun getInfo() = "$model : Installed on $cpu with $size Gb"       // 바깥 클래스의 프로퍼티 접근(private 이어도 가능)
    }

    // 지역 클래스 : 블록 범위 내부에서만 유효
    fun powerOn(): String {
        class Led(val color: String) {
            fun blink(): String = "Blinking $color on $model"                   // 외부의 프로퍼티는 접근 가능
        }
        val powerStatus = Led("Red")                                      // 여기서 지역클래스가 사용
        val powerSwitch = object : Switcher {                                   // 익명 객체를 사용해 Switcher 의 on() 을 구현
            override fun on(): String {
                return powerStatus.blink()
            }
        }
        return powerSwitch.on()                                                 // 익명 객체의 메서드 사용
    }
}

// 익명 객체를 위한 인터페이스 추가하기
interface Switcher {
    fun on(): String
}

fun main() {
    val mySdcard = Smartphone("57").ExternalStorage(32)
    println(mySdcard.getInfo())

    val myPhone = Smartphone("Note10")
    myPhone.ExternalStorage(128)
    println(myPhone.powerOn())
}