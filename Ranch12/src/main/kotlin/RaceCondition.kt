// 경합 상태 방지하기
class Weapon2(val name: String)
class Player2 {
    var weapon: Weapon2? = Weapon2("Ebony Kris")

    fun printWeaponName() {
//        if (weapon != null)
//            println(weapon.name)
        // also를 사용하여 해결
        weapon?.also {
            println(it.name)
        }
    }
}

fun main() {
    Player2().printWeaponName()
}