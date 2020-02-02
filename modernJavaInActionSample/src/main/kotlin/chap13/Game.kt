package chap13

fun main() {
    val list = listOf(Ellipse(), Triangle())
    Utils.paint(list)
}

object Utils {
    fun paint(l: List<Resizable>) {
        l.forEach { r -> r.setAbsoluteSize(42, 42) }

        l.forEach { r -> r.setRelativeSize(2, 2) }
    }
}

interface Drawable {
    fun draw()
}

interface Resizable : Drawable {
    fun getWidth(): Int
    fun getHeight(): Int
    fun setWidth(width: Int)
    fun setHeight(height: Int)
    fun setAbsoluteSize(width: Int, height: Int)

    fun setRelativeSize(widthFactor: Int, heightFactor: Int) {
        setAbsoluteSize(getWidth() / widthFactor, getHeight() / heightFactor)
    }
}

class Ellipse : Resizable {
    override fun draw() {}

    override fun getWidth(): Int {
        return 0
    }

    override fun getHeight(): Int {
        return 0
    }

    override fun setWidth(width: Int) {}

    override fun setHeight(height: Int) {}

    override fun setAbsoluteSize(width: Int, height: Int) {}
}

class Triangle : Resizable {
    override fun draw() { }

    override fun getWidth(): Int {
        return 0
    }

    override fun getHeight(): Int {
        return 0
    }

    override fun setWidth(width: Int) {}

    override fun setHeight(height: Int) {}

    override fun setAbsoluteSize(width: Int, height: Int) {}
}