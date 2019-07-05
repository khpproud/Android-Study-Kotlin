package ch05_class

// 파생 클래스 만들어 보기
// 상속 가능한 클래스를 선언하기 위해 open 사용
open class Bird(var name: String, var wing: Int, var beak: String, var color: String) {
    fun fly() = println("Fly wing: $wing")
    fun sing(vol: Int) = println("Sing vol: $vol")
    override fun toString(): String {
        return "Bird(name='$name', wing=$wing, beak='$beak', color='$color')"
    }
}

// 주 생성자를 사용하는 상속
class Lark(name: String, wing: Int, beak: String, color: String) : Bird(name, wing, beak, color) {
    fun singHitone() = println("Happy song!")
}

// 부 생성자를 사용하는 상속
class Parrot : Bird {
    val language: String

    constructor(name: String, wing: Int, beak: String, color: String, language: String) : super(name, wing, beak, color) {
        this.language = language
    }

    fun speak() = println("Speak! $language")
}

fun main() {
    val coco = Bird("myBird", 2, "short", "blue")
    val lark = Lark("myLark", 2, "long", "brown")
    val parrot = Parrot("myParrot", 2, "short", "multiple", "Korean")

    println("Coco : $coco")
    println("Lark : $lark")
    println("Parrot : $parrot, ${parrot.language}")
    lark.singHitone()
    parrot.speak()
    lark.fly()
}