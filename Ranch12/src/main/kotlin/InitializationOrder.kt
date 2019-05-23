/**
 * 초기화 순서 비교
 */
class Player3(_name: String, val health: Int) {
    val race = "DWARF"
    var town = "Bavaria"
    val name = _name
    val alignment: String
    private var age = 0

    init {
        println("initializing player")
        alignment = "GOOD"
    }

    constructor(_name: String): this(_name, 100) {
        town = "The Shire"
    }
}

/**
 * 초기화 순서를 고려한 코드 작성
 */
class Player4() {
    val name: String
    private fun firstLetter() = name[0]

    init {
        name = "Madrigal"
        println(firstLetter())
    }
}

fun main() {
//    Player3("Madrigal")
    Player4()
}