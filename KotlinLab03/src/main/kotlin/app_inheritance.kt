class Shape1 {
    var x: Int = 0
    var y: Int = 0
    var name: String = "Rect"

    fun draw() {
        println("draw $name : location : $x, $y")
    }
}

// 프로퍼티 재정의
open class Super {
    open val name: String = "Park"
    open var age: Int = 10
    open val email: String? = null
    open val address: String = "seoul"
}

class Sub: Super() {
    override var name: String = "Kim"
    //override val age: Int = 20            // X var -> val
    override val email: String = "b@b.com"
    //override val address: String? = null  // X null x -> nullable
}

// super 에 의한 상위클래스 멤버 접근
open class Super2 {
    open var x: Int = 10
    open fun someFun() {
        println("Super ... someFun()")
    }
}

class Sub2: Super2() {
    override var x: Int = 20
    override fun someFun() {
        super.someFun()
        println("Sub2... ${super.x}.... $x")
    }
}

open class Super3 {
    constructor(name: String, no: Int) {
        println("Super ... constructor(name, no)")
    }
    init {
        println("Super ... init call...")
    }
}

class Sub3(name: String): Super3(name, 10) {
    constructor(name: String, no: Int): this(name) {
        println("Sub... constructor(name, no) call")
    }
    init {
        println("Sub... init call")
    }
}

fun main() {
    val obj1: Any = Shape1()
    val obj2: Any = Shape1()
    val obj3 = obj1
    println("obj1.equals(obj2) is ${obj1.equals(obj2)}")
    println("obj1.equals(obj3) is ${obj1.equals(obj3)}")

    val rect = Rect()
    rect.name = "Rect"
    rect.x = 10
    rect.y = 10
    rect.width = 20
    rect.height = 20
    rect.print()

    val circle = Circle()
    circle.name = "Circle"
    circle.x = 30
    circle.y = 30
    circle.r = 5
    circle.print()

    var sub = Sub2()
    sub.someFun()

    Sub3("Park")
    println(".............")
    Sub3("Park", 10)
}