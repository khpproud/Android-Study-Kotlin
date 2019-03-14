open class Shape {
    var x: Int = 0
    set(value) {
        if (value < 0)
            field = 0
        else field = value
    }

    var y: Int = 0
    set(value) {
        if (value < 0)
            field = 0
        else
            field = value
    }

    lateinit var name: String

    open fun print() {
        println("$name : location : $x, $y")
    }
}