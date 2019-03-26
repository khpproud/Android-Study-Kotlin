package four

// 트럼프 카드 덱을 중위 메소드와 열거형으로 유연하게 표현한 예
enum class Suit {
    HEARTS,
    SPADES,
    CLUBS,
    DIAMONDS
}

enum class Rank(val num: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(11), QUEEN(12), KING(13),
    ACE(1);

    // 구문을 간소화하기 위한 새로운 중위메소드 of를 추가
    infix fun of(suit: Suit) = Card(this, suit)
}

data class Card(val rank: Rank, val suit: Suit)

fun main() {
    // Card 클래스의 인스턴스 생성
    val card1 = Card(Rank.KING, Suit.HEARTS)         // Heard King

    val card2 = Rank.KING.of(Suit.HEARTS)
    // of는 중위 메소드로 지정했으므로 ., () 생략 가능
    val card3 = Rank.KING of Suit.HEARTS
}