open class Super6 {
    val publicData: Int = 10
    protected val protectedData: Int = 20
    private val privateData: Int = 30
}

class Sub6: Super6() {
    fun visibilityTest() {
        println("$publicData .. ")
        println("$protectedData ..")
        //println("$privateData ..") // 에러
    }
}

class SomeClass {
    fun visibilityTest() {
        val obj = Super6()
        println("${obj.publicData} ..")
        //println("${obj.protectedData} ..") // 에러
        //println("${obj.privateData} ..")  // 에러
    }
}

class PropertyVisibilityTest {
    private var data: Int = 10

    fun getData(): Int {
        return data
    }
}

// 프로퍼티 getter/setter
private var data2: Int = 10
get() = field
set(value) {
    field = value
}

// set()/get() 을 이용한 접근 제한
class PropertyVisibilityTest2 {
    var data: Int = 10
    private set(value) {
        field = value
    }
}

fun main() {
    val obj = PropertyVisibilityTest()
    println("${obj.getData()} ..")

    val obj2 = PropertyVisibilityTest2()
    println("${obj2.data}")
    //obj2.data = 20
}