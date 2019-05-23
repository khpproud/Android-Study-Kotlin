/**
 *  공변(covariance) : out, 반공변(contravariance) : in 사용 예
 */

class Barrel<out T>(val item: T)

class Barrel2<in T>(item: T)

fun main() {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("평범한 중절모", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))

    // 슈퍼 - 서브
    lootBarrel = fedoraBarrel
    // 스마트 캐스트 : lootBarrel의 item 값과 타입이 변경되지 않으므로 타입을 변환해도 안전
    val myFedora: Fedora = lootBarrel.item

    var fedoraBarrel2: Barrel2<Fedora> = Barrel2(Fedora("평범한 중절모", 15))
    var lootBarrel2: Barrel2<Loot> = Barrel2(Coin(15))

    // 서브 - 슈퍼
    fedoraBarrel2 = lootBarrel2

}


