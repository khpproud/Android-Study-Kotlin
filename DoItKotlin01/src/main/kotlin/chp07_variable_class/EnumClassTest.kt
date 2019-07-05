package chp07_variable_class

// 인터페이스를 통한 열거형 클래스 구현 예
interface Score {
    fun getScore(): Int
}

enum class MemberType(var prio: String) : Score {           // Score 를 구현할 열거형 클래스
    NORMAL("Third") {
        override fun getScore(): Int = 100
    },
    SILVER("Second") {
        override fun getScore(): Int = 500
    },
    GOLD("First") {
        override fun getScore(): Int = 1500
    }
}

fun main() {
    println("MemberType.NORMAL.getScore() = ${MemberType.NORMAL.getScore()}")
    println("MemberType.GOLD = ${MemberType.GOLD}")
    println("MemberType.valueOf(SILVER) = ${MemberType.valueOf("SILVER")}")
    println("MemberType.SILVER.prio = ${MemberType.SILVER.prio}")

    for (grade in MemberType.values()) {
        println("grade.name = ${grade.name}, prio = ${grade.prio}")
    }
}