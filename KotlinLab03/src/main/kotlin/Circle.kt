class Circle: Shape() {
    var r: Int = 0
    set(value) {
        if (value < 0)
            field = 0
        else
            field = value
    }

    override fun print() {
        println("$name : location : $x, $y ... redius : $r")
    }
}