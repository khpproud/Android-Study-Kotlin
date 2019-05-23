/**
 * 제네릭 사용법 및 예
 */
// 제네릭 클래스 - 게임 플레이어에게 보상으로 주는 아이템을 보관하는 상자
// 제네릭 타입 제약 : Loot 클래스 및 그 하위 클래스만 가능
// 가변 인자를 vararg 키워드를 사용하여 인자로 받음
class LootBox<T: Loot>(vararg item: T) {
    var open = false
    private var loot: Array<out T> = item

    // 연산자 재정의 인덱스 연산자[] -> get
    operator fun get(index: Int): T? = loot[index].takeIf { open }

    // 제네릭 함수
    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    // 복합 제네릭 함수
    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
    }
}

open class Loot(val value: Int)

// 인스턴스 생성
class Fedora(val name: String, value: Int): Loot(value)
class Coin(value: Int): Loot(value)

fun main() {
    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("평범한 중절모", 15), Fedora("눈부신 자주색 중절모", 25))
    val lootBoxTwo = LootBox(Coin(10))

    lootBoxOne.open = true
    lootBoxOne.fetch(1)?.run {
        println("$name 를 LootBox 에서 꺼냈습니다!")
    }

    val coin = lootBoxOne.fetch(0) {
        Coin(it.value * 3)
    }
    coin?.let { println(it.value) }

    // 재정의된 get 함수가 호출
    val fedora = lootBoxOne[1]
    fedora?.let { println(it.name) }
}