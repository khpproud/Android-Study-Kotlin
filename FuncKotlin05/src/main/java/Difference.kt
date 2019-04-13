package chap05

// 화장함수와 상속의 차이
open class Canine {
    open fun speak() = "<일반적인 개과 소리>"
}

class Dog: Canine() {
    override fun speak() = "멍멍!!"
}

fun printSpeak(canine: Canine) {
    println(canine.speak())
}

// 확장함수에서는
open class Feline
fun Feline.speak() = "<일반적인 고양잇과 소리>"

class Cat: Feline()
fun Cat.speak() = "야옹!!"

fun printSpeak(feline: Feline) {
    println(feline.speak())
}

// 좀더 자세하게
open class Primate(val name: String)

fun Primate.speak() = "$name: <일반적인 영장류 소리>"

open class GiantApe(name: String): Primate(name)

fun GiantApe.speak() = "${this.name} : 거대 유인원 소리!!"

fun printSpeak(primate: Primate) {
    println(primate.speak())
}

fun main() {
    // speak 의 행동이 다름 - 상속이기 때문에
    printSpeak(Canine())
    printSpeak(Dog())

    // 확장함수는 Override 표시가 없다 즉 상속되지 않았음
    printSpeak(Feline())
    printSpeak(Cat())

    printSpeak(Primate("쿄쿄"))
    printSpeak(GiantApe("쾅"))
}