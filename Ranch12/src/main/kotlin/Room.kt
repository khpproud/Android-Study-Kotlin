open class Room(val name: String) {
    protected open val dagerLevel = 5

    fun description() = "Room: $name\n위험 수준: $dagerLevel"

    open fun load() = "아무도 여기에 오지 않았습니다..."
}

open class TownSquare: Room("Town Square") {
    override val dagerLevel = super.dagerLevel - 3
    private var bellSound = "땡땡"

    override fun load() = "당신의 참여를 주민들이 다 함께 환영합니다!\n${ringBell()}"

    fun ringBell() = "당신의 도착을 종탑에서 알립니다. $bellSound"
}
