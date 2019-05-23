open class Weapon(val name: String, val type: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Weapon

        if (name != other.name) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}

fun main() {
    // equals()와 hashCode()를 Override 하여 객체의 내용 비교
    println(Weapon("ebony kris", "dagger") == Weapon("ebony kris", "dagger"))
}