import java.io.File
import extensions.random

/**
 * 코틀린에서 제공하는 문자열의 다양한 함수 사용 예
 */
const val TAVERN_NAME = "David's Folly"

val patronList = mutableListOf<String>("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()

// Map 사용
val patronGold = mutableMapOf<String, Double>()

val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main() {
    println(frame("Welcome, David!", 5))
    println("Welcome, David!".frame2(5))

    (0.. 9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"

        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    println(patronGold)

    uniquePatrons.forEachIndexed { index, patron ->
        println("Good evening, $patron - You're #${index + 1} in line.")
        placeOrder(patron, menuList.random())
    }

    // 쫓겨난 고객을 고객 명단 에서 삭제
    uniquePatrons.removeIf {
        patron -> !patronGold.containsKey(patron)
    }

    println(uniquePatrons)
    displayPatronBalances()
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance :${"%.2f".format(balance)}")
    }
}

// 메뉴 정렬 출력
fun printMenuAligned() {
    val maxWordsInLine  = 34
    val welcomeMsg = "Welcome to Taernyl's Folly"
    val remainingBlank = maxWordsInLine - welcomeMsg.length - 2
    run {
        print("*".repeat(remainingBlank / 2))
        print(" $welcomeMsg ")
        println("*".repeat(remainingBlank / 2))
    }

    menuList.forEach {
        val (_, name, price) = it.split(",")
        val nameLength = name.length
        val priceLength = price.length
        val remainingLength = maxWordsInLine - nameLength - priceLength
        println("${name.capitalize()}${".".repeat(remainingLength)}$price")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    val remainingPurse = totalPurse - price
    if (remainingPurse < 0.0) {
        // 돈이 없으므로 쫓아냄
        println("$patronName 은 돈이 없으므로 쫓겨남!!!")
        //uniquePatrons.remove(patronName)
        patronGold.remove(patronName)
    } else {
        patronGold[patronName] = remainingPurse
    }

}


private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    val (type, name, price) = menuData.split(',')
    val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다"
    println("$patronName 은 $tavernMaster 에게 주문한다")
    println(message)

    val phrase = "Wow! $name Very good!"

    // 문자열 비교 '==' 자바에서는 equals 사용 ,참조 비교 '===' 자바에서는 '=='
    val phrase2 = if (name == "Dragon's Breath") {
//        "$patronName 이 감탄한다 : ${toDragonSpeak(phrase)}"
        "$patronName 이 감탄한다 : ${phrase.toDragonSpeak()}"
    } else {
        "$patronName 이 말한다 : Thank you $name"
    }
    println(phrase2)

    val priceVal: Double = price.toDoubleOrNull() ?: 0.0

    performPurchase(price.toDouble(), patronName)
}

// 모음을 특정 단어로 변경
private fun String.toDragonSpeak(): String {
    return this.replace(Regex("[aeiouAEIOU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }
    }
}

// frame 함수
fun frame(name: String, padding: Int, formatChar: String = "*"): String {
    val greeting = name
    val middle = formatChar.padEnd(padding)
        .plus(greeting)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}

// String의 확장 함수로 변환
fun String.frame2(padding: Int, formatChar: String = "*"): String {
    val middle = formatChar.padEnd(padding)
        .plus(this)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}