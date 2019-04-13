package chap05

// 오브젝트용 확장 함수
object Builder {

}

fun Builder.buildBridge() = "new Bridge..."

class Designer {
    companion object {

    }

    object Desk {

    }
}

fun Designer.Companion.fastPrototype() = "ProtoType"
fun Designer.Desk.portfolio() = listOf("Project1, Project2")

fun main() {
    println(Designer.fastPrototype())
    Designer.Desk.portfolio().forEach(::println)

}