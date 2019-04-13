package chap05

// 인덱싱된 접근 예
// get
operator fun Pack.get(name: String) = members[name]!!

// set
enum class WolfRelationships {
    FRIEND, SIBLING, ENEMY, PARTNER
}

operator fun Wolf.set(relationships: WolfRelationships, wolf: Wolf) {
    println("${wolf.name} 은 내 새로운 $relationships 이다")
}

fun main() {
    val talbot = Wolf("Talbot")
    val northPack: Pack = talbot + Wolf("Big bursa")
    val biggerPack = northPack + Wolf("Bad Wolf")

    val badWolf = biggerPack["Bad Wolf"]

    println(badWolf.name)

    talbot[WolfRelationships.ENEMY] = badWolf
}