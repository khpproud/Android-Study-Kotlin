class Rect: Shape() {
    var width: Int = 0
    set(value) {
        if (value < 0)
            field = 0
        else
            field = value
    }

    var height: Int = 0
    set(value) {
        if (value < 0)
            field = 0
        else
            field = value
    }

    override fun print() {
        println("$name : location : $x, $y ... width : $width ... height : $height")
    }
}