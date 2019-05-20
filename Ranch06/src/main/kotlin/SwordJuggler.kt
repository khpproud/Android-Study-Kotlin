fun main() {
    // 예외 처리 예
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1.. 3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    // try-catch 추가
    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("$swordsJuggling 개의 칼로 저글링합니가!")
}

// IllegalStateException 예외 던지기
fun proficiencyCheck(swordJuggling: Int?) {
//    swordJuggling ?: throw IllegalStateException("플레이어가 저글링을 할 수 없음")
//    swordJuggling ?: throw UnSkilledSwordJugglingException()
    // 전제 조건(precondition function) 함수 사용
    //checkNotNull(swordJuggling) { "플레이어가 저글링을 할 수 없음" }
//    requireNotNull(swordJuggling) { "플레이어가 저글링을 할 수 없음" }
    error("플레이어가 저글링을 할 수 없음")
}

// Custom 예외 던지기
class UnSkilledSwordJugglingException: IllegalStateException("플레이어가 저글링 할 수 없음")