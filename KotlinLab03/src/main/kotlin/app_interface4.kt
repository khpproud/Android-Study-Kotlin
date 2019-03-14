package ten_two_three
// 같은 이름의 추상 함수 여러개
interface Interface1 {
    fun funA()
}

interface Interface2 {
    fun funA()
}

abstract class Super2 {
    abstract fun funA()
}

class Sub2: Super2(), Interface1, Interface2 {
    override fun funA() {
        println("Sub2 funA...")
    }
}

// 같은 이름의 추상 함수와 구현된 함수
interface Interface3 {
    fun funA() {
        println("Interface3 funA...")
    }
}

abstract class Super3 {
    abstract fun funA()
}

class Sub3: Super3(), Interface3 {
    override fun funA() {
        super.funA()
        println("Sub3 funA...")
    }
    fun some() {
        super.funA()
    }
}

// 같은 이름으로 구현된 함수가 여러개
interface Interface4 {
    fun funA() {
        println("Interface4 funA()...")
    }
}

interface Interface5 {
    fun funA() {
        println("Interface5 funA()...")
    }
}

class Sub4: Interface4, Interface5 {
    override fun funA() {
        super<Interface4>.funA()
        super<Interface5>.funA()
    }

    fun some() {
        super<Interface5>.funA()
    }
}

fun main() {
    val obj1 = Sub2()
    obj1.funA()

    val obj2 = Sub3()
    obj2.funA()
    obj2.some()

    val obj3 = Sub4()
    obj3.funA()
    obj3.some()
}