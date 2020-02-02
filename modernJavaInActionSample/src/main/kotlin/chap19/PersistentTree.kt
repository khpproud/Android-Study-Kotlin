package chap19

fun main() {
    val t = Tree("Mary", 22,
        Tree("Emily", 20,
            Tree("Alan", 50, null, null),
            Tree("Georgie", 23, null, null)),
        Tree("Tian", 29,
            Tree("Raoul", 23, null, null),
            null)
    )

    println("Raoul: ${lookup("Raoul", -1, t)}") // find 23
    println("Jeff: ${lookup("Jeff", -1, t)}")   // not find -1

    val f = fupdate("Jeff", 80, t)
    println("Jeff: ${lookup("Jeff", -1, f)}")   // find 80

    val u = update("Jim", 40, t)
    // not changed t
    println("Jeff: ${lookup("Jeff", -1, t)}")
    println("Jim: ${lookup("Jim", -1, u)}")     // find 40

    val f2 = fupdate("Jeff", 80, t)
    println("Jeff: ${lookup("Jeff", -1, f2)}")  // find 80
    // 'Jim' in f2
    println("Jim: ${lookup("Jim", -1, f2)}")
}

data class Tree(val key: String, var value: Int, var left: Tree?, var right: Tree?) {
    override fun toString(): String {
        return String.format("$key:$value")
    }
}

private fun lookup(k: String, defaultVal: Int, t: Tree?): Int {
    if (t == null) {
        return defaultVal
    }
    if (k == t.key) {
        return t.value
    }
    return lookup(k, defaultVal, if (k < t.key) t.left else t.right)
}

private fun update(k: String, newVal: Int, t: Tree?): Tree? {
    var temp = t
    when {
        temp == null -> {
            temp = Tree(k, newVal, null, null)
        }
        k == temp.key -> {
            temp.value = newVal
        }
        k < temp.key -> {
            temp.left = update(k, newVal, temp.left)
        }
        else -> {
            temp.right = update(k, newVal, temp.right)
        }
    }
    return temp
}

private fun fupdate(k: String, newVal: Int, t: Tree?): Tree? {
    return when {
        t == null -> {
            Tree(k, newVal, null, null)
        }
        k == t.key -> {
            Tree(k, newVal, t.left, t.right)
        }
        k < t.key -> {
            Tree(t.key, t.value, fupdate(k, newVal, t.left), t.right)
        }
        else -> {
            Tree(t.key, t.value, t.left, fupdate(k, newVal, t.right))
        }
    }
}