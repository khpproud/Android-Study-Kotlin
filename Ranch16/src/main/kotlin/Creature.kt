import java.util.*

/**
 * 전투 가능 객체의 인터페이스 정의
 */
interface Fightable {
    /**
     * HP
     */
    var healthPoints: Int
    /**
     * 전투에 사용하는 주사위 개수
     */
    val diceCount: Int
    /**
     * 주사위의 면 수
     */
    val diceSides: Int
    /**
     * 주사위를 굴려 나온 수의 합계
     */
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random().nextInt(diceSides) + 1
        }.sum()

    /**
     * 공격 함수
     */
    fun attack(opponent: Fightable): Int
}

/**
 * 각종 괴물들의 기반이 되는 Monster 추상 클래스
 */
abstract class Monster(val name: String, val description: String, override var healthPoints: Int) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

/**
 * 몬스터 타입 1.고블린
 */
class Goblin(name: String = "Goblin", description: String = "Ugly Goblin", healthPoints: Int = 30) :
    Monster(name, description, healthPoints) {
    override val diceCount: Int = 2
    override val diceSides: Int = 8
}