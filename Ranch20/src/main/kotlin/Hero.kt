@file:JvmName("Hero")

import java.io.IOException
import java.lang.Exception

fun main() {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFirendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "아무 의미 없는 문자")

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.dec())
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "Hello, Hero!"
    println(adversary.utterGreeting())

    adversary.offerFood()

    try {
        adversary.extendHandInFriendship()
    } catch (e: Exception) {
        println("Farewell, bad monster!")
    }
}

fun makeProclamation() = "Hello, Monster!"

// 기본 매개 변수를 갖는 함수를 추가
@JvmOverloads
fun handOverFood(leftHand: String ="Strawberry", rightHand: String = "Meat") {
    println("Delicious $leftHand and $rightHand 를 넘겨 주었습니다.")
}

@Throws(IOException::class)
fun acceptApology() {
    throw IOException()
}

// 자바의 함수 타입
val translator = { utterance: String -> println(utterance.toLowerCase().capitalize()) }

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms.L", "Lay on Hands")

    companion object {
        @JvmField
        val MAX_SPELL_COUNT = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("I am great knight!")
    }
}