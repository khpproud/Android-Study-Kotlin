/**
 * reified 키워드 사용 예
 * 컴파일된 JVM 바이트코드에는 제네릭 타입 매개변수 정보가 수록되지 않고 소거됨
 * 런타임시 다음의 문제가 생김
 * 1. 제네릭 타입 매개변수가 지정된 클래스로 인스턴스를 생성할 때 어떤 타입의 인자가 사용되었는지 알기위해 타입 검사 불가능
 * 2. 제네릭 타입 매개변수가 지정된 제네릭 클래스의 인스턴스 타입이 해당 클래스의 인스턴스인지 검사할 수 없음
 *
 * 이를 해결위한 reified 키워드 사용
 */
fun main() {
    val list = listOf(1, 2)
    // 제네릭 타입 매개변수가 지정된 클래스는 타입 검사에 사용 X
//    if (list is List<String>) {
//        println("List<String> 타입!")
//    }

    randomOrBackupLoot {
        Fedora("대체용 중절모", 15)
    }.run {
        // 대체용 또는 고풍스런 중절모 중 하나를 출력
        println(name)
    }
}

// inline 과 reified 키워드 사용
inline fun <reified T> randomOrBackupLoot(backupLoot: () -> T): T {
    val items = listOf(Coin(14), Fedora("고풍스런 중절모", 150))
    val randomLoot: Loot = items.shuffled().first()
    return if (randomLoot is T) {
        randomLoot
    } else {
        backupLoot()
    }
}