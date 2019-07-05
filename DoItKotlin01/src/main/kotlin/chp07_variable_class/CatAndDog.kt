package chp07_variable_class

open class Animal(val name: String)

// feeding 의 구현을 위해 인터페이스 Pet 지정
class Dog(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "Dog"
    override fun feeding() {
        println("Feed the dog a bone")
    }
}

class Master {
    fun playWithPet(pet: Pet) {
        println("Enjoy with my ${pet.species}")
    }
//    fun playWithPet(cat: Cat) {
//        println("Enjoy with my cat")
//    }
}

fun main() {
    val master = Master()
    val dog = Dog("Toto", "small")
    val cat = Cat("Coco", "BigFat")
    master.playWithPet(dog)
    master.playWithPet(cat)
}