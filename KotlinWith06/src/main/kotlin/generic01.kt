package six.one

// 형식 매개변수의 null 가능성
class Action(val name: String)
class ActionGroup<T: Action?>(private val list: List<T>) {
    fun last(): T = list.last()
    fun lastOrNull(): T? = list.lastOrNull()
}

fun main() {
    val actionGroup = ActionGroup<Action>(listOf())

    //val action = actionGroup.last()
    val action2 = actionGroup.lastOrNull()

    //println(action.name)
    println(action2?.name)

    val actionGroup2 = ActionGroup(listOf(Action("Bart"), null))
    println(actionGroup2.lastOrNull())

}