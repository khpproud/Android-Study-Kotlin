package chap05

// 확장 함수가 선언된 클래스의 인스턴스를 디스패치 리시버라 함
open class Caregiver(val name: String) {
    open fun Feline.react() = "크엉!!!"
    fun Primate.react() = "*$name 은(는) ${this@Caregiver.name} (와)과 같이 논다* "

    fun takeCare(feline: Feline) {
        println("고양잇과 반응 : ${feline.react()}")
    }
    fun takeCare(primate: Primate) {
        println("영장류 반응 : ${primate.react()}")
    }
}

open class Vet(name: String): Caregiver(name) {
    override fun Feline.react() = "*$name (으)로 부터 도망친다*"
}

fun main() {
    val adam = Caregiver("Adam")
    val suzi = Cat()
    val koko = Primate("Koko")

    adam.takeCare(suzi)
    adam.takeCare(koko)

    val brenda = Vet("Brenda")
    listOf(adam, brenda).forEach { caregiver ->
        println("${caregiver.javaClass.simpleName} ${caregiver.name}")
        caregiver.takeCare(suzi)
        caregiver.takeCare(koko)
    }

}


// this의 범위
class Dispatcher {
    // 이 this 는 클래스의 인스턴스
    val dispatcher: Dispatcher = this
    fun Int.extansion() {
        // 확장 함수 내에서 this 는 리시버 타입의 인스턴스를 가리킴
        val receiver: Int = this
        val dispatcher: Dispatcher = this@Dispatcher
    }
}
