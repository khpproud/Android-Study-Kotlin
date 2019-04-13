package chap03.immutable

// 참조 불변성의 예 - 인스턴스에 대한 값 변경을 제한할 수는 없음
class MutableObj {
    var value = ""
    override fun toString(): String {
        return "MutableObj(value = '$value')"
    }
}

fun main() {
    val mutableObj: MutableObj = MutableObj()
    println("mutableObj : $mutableObj")
    mutableObj.value = "Changed"
    println("mutableObj : $mutableObj")
    val list = mutableListOf("a", "b", "c", "d", "e")
    println(list)
    list.add("f")
    println(list)
}