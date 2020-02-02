package chap15

fun main() {
    test1()
    println("-----------------")
    test2()
}

private fun test1() {
    val c3 = ArithmeticCell("C3")
    val c1 = SimpleCell("C1")
    val c2 = SimpleCell("C2")

    c1.subscribe(c3::setLeft)
    c2.subscribe(c3::setRight)

    c1.onNext(10)
    c2.onNext(20)
    c1.onNext(15)
}

private fun test2() {
    val c5 = ArithmeticCell("C5")
    val c3 = ArithmeticCell("C3")
    val c4 = SimpleCell("C4")
    val c2 = SimpleCell("C2")
    val c1 = SimpleCell("C1")

    c1.subscribe(c3::setLeft)
    c2.subscribe(c3::setRight)

    c3.subscribe(c5::setLeft)
    c4.subscribe(c5::setRight)

    c1.onNext(10)
    c2.onNext(20)
    c1.onNext(15)
    c4.onNext(1)
    c4.onNext(3)
}

data class ArithmeticCell(
    val name: String,
    private var left: Int = 0,
    private var right: Int = 0
) : SimpleCell(name) {

    fun getLeft() = left

    fun getRight() = right

    fun setLeft(left: Int) {
        this.left = left
        onNext(left + right)
    }

    fun setRight(right: Int) {
        this.right = right
        onNext(left + right)
    }
}