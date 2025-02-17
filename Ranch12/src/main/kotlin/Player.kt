import java.io.File

class Player(_name: String, var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean) {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        internal set(value) {
            field = value.trim()
        }

    val hometown: String by lazy { selectHometown() }
    var currentPosition = Coordinate(0, 0)

    init {
        require(healthPoints > 0) {"healthPoints는 0보다 커야 합니다!"}
        require(name.isNotBlank()) {"Player는 이름이 있어야 합니다!"}
    }

    constructor(name: String): this(name, isBlessed = true, isImmortal = false)

    fun castFireball(numFireballs: Int = 2) =
            println("한 덩어리의 파이어볼이 나나탄다. (x$numFireballs)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        return if (auraVisible) "GREEN" else "NONE"
    }

    fun formatHealthStatus() =
            when (healthPoints) {
                100 -> "최상의 상태임!"
                in 90.. 99 -> "약간의 찰과상만 있음!"
                in 75.. 89 -> if (isBlessed) {
                    "경미한 상처가 있지만 빨리 치유되고 있음!"
                } else {
                    "경미한 상처만 있음!"
                }
                in 15.. 74 -> "많이 다친것 같음."
                else -> "최악의 상태임!"
            }

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()
}