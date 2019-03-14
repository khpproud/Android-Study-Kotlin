package sixteen_one

// 확장 함수 이용 에러 상황
open class Super {
    open fun superFun() {
        println("Super... superFun...")
    }
}

class Sub: Super() {
    var data: Int = 20
    override fun superFun() {
        println("Sub... superFun... $data")
    }

    fun some1(data: Int) {
        this.data = data
        superFun()
        super.superFun()
    }
}

fun Sub.some2(data: Int) {
    this.data = data
    superFun()
    //super.superFun()  // 에러 - 실행시에 판단
}

fun main() {
    val obj: Sub = Sub()
    obj.some1(10)
    obj.some2(100)
}