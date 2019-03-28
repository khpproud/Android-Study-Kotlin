package seven.collection.three

class Result(
    val player: Player,
    val catefory: Category,
    val result: Double
)

class Player(val name: String)
enum class Category { SWIMMING, RUNNING, CYCLING }

fun main() {
    val alex = Player("Alex")
    val max = Player("Max")
    val bob = Player("Bob")
    // 사용할 데이터
    val results = listOf(
        Result(alex, Category.SWIMMING, 23.4),
        Result(alex, Category.RUNNING, 43.2),
        Result(alex, Category.CYCLING, 15.3),
        Result(max, Category.SWIMMING, 17.3),
        Result(max, Category.RUNNING, 33.3),
        Result(bob, Category.SWIMMING, 29.9),
        Result(bob, Category.CYCLING, 18.0)
    )

    // 종목별 최고 선수를 찾음
    val bestInCategory = results.groupBy { it.catefory }
        .mapValues { it.value.maxBy { it.result }?.player?.name }
    println(bestInCategory)
}
