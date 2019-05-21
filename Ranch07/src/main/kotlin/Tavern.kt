import java.io.File
import kotlin.math.roundToInt

/**
 * 코틀린에서 제공하는 문자열의 다양한 함수 사용 예
 */
const val TAVERN_NAME = "David's Folly"

//var playerGold = 10
//var playerSilver = 10

val patronList = mutableListOf<String>("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()

// Map 사용
//val patronGold = mapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)
val patronGold = mutableMapOf<String, Double>()

val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main() {
//    placeOrder("Shandy,Dragon's Breath,5.91")
//    placeOrder("Sam", "elixir,Shirley's Temple,4.12")

    // 유니코드 문자 출력
//    val omSymbol = '\u0950'
//    println(omSymbol)

    // 메뉴 데이터 출력
//    patronList.forEachIndexed { index, patron ->
//        println("Good evening, $patron - You're #${index + 1} in line.")
////        placeOrder(patron, "shandy,Dragon's Breath,5.91")
//        placeOrder(patron, menuList.shuffled().first())
//    }
//
//    menuList.forEachIndexed { index, data ->
//        println("$index : $data")
//    }

    (0.. 9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"

        uniquePatrons += name
    }
//    println(uniquePatrons)
//    printMenuAligned()

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    println(patronGold)

    uniquePatrons.forEachIndexed { index, patron ->
        println("Good evening, $patron - You're #${index + 1} in line.")
        placeOrder(patron, menuList.shuffled().first())
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

//fun performPurchase(price: Double) {
//    displayBalance()
//    val totalPurse = playerGold + (playerSilver / 100.0)
//    println("지갑 전체 금액 : 금화 $totalPurse")
//    println("금화 $price 로 술 구입")
//
//    val remainingBalance = totalPurse - price
//    if (remainingBalance < 0.0) {
//        println("금액 부족 : 술을 구입할 수 없음!!!")
//        return
//    }
//
//    println("남은 금액 : $remainingBalance")
//
//    // Double 타입 값의 형식 지정하기 - 소수점 이하 두자리까지 반올림
//    println("남은 잔액 : ${"%.2f".format(remainingBalance)}")
//
//    // Double 타입 값을 Int 타입 값으로 변환
//    val remainingGold = remainingBalance.toInt()
//    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
//    playerGold = remainingGold
//    playerSilver = remainingSilver
//    displayBalance()
//}

//private fun displayBalance() {
//    println("플레이어의 지갑 잔액 = 금화 : $playerGold 개, 은화 : $playerSilver 개")
//}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    val (type, name, price) = menuData.split(',')
    val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다"
    println("$patronName 은 $tavernMaster 에게 주문한다")
    println(message)

    val phrase = "Wow! $name Very good!"
//    val phrase = " WOW! $name VERY GOOD"
//    println("$patronName 이 감탄한다 : ${toDragonSpeak(phrase)}")

    // 문자열은 불변
//    println(phrase)

    // 문자열 비교 '==' 자바에서는 equals 사용 ,참조 비교 '===' 자바에서는 '=='
    val phrase2 = if (name == "Dragon's Breath") {
        "$patronName 이 감탄한다 : ${toDragonSpeak(phrase)}"
    } else {
        "$patronName 이 말한다 : Thank you $name"
    }
    println(phrase2)

    val priceVal: Double = price.toDoubleOrNull() ?: 0.0
//    performPurchase(priceVal)
//    performPurchase(priceVal)
//    performPurchase(priceVal)

    performPurchase(price.toDouble(), patronName)
}

// 모음을 특정 단어로 변경
private fun toDragonSpeak(phrase: String): String {
    return phrase.replace(Regex("[aeiouAEIOU]")) {
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