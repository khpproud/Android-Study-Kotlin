package chp07_variable_class

interface Pet {
    var category: String                // abstract 키워드가 없어도 기본적으로 추상 프로퍼티
    val msgTags: String
    get() = "I'm your lovely pet!"
    var species: String

    fun feeding()
    fun patting() {                     // 구현부를 포함한 일반 메서드
        println("Keep patting!")
    }
}

class Cat(name: String, override var category: String) : Pet, Animal(name) {
    override var species: String = "Cat"
    override fun feeding() {
        println("Feed the cat a tuna can!")
    }
}

fun main() {
    val obj = Cat("Coco","small")
    println("Pet Category : ${obj.category}")
    obj.feeding()
    obj.patting()
}