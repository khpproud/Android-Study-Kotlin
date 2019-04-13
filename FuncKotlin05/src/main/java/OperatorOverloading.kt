package chap05

// 연산자 오버로딩의 예
class Wolf(val name: String) {
    operator fun plus(wolf: Wolf) = Pack(mapOf(name to this, wolf.name to wolf))
}

class Pack(val members:Map<String, Wolf>)

// Binary Operator
operator fun Pack.plus(wolf: Wolf) = Pack(this.members.toMutableMap() + (wolf.name to wolf))

fun main() {
    val talbot = Wolf("Talbot")
    val northPack: Pack = talbot + Wolf("Big bursa")

    northPack.members.forEach {
        println(it.key)
    }

    val biggerPack = northPack + Wolf("Bad Wolf")
    biggerPack.members.forEach {
        println(it.key)
    }
}