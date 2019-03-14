package nineteen_one

// 델리게이션 패턴
class MyDelegatee {
    fun print(str: String) {
        println("i am delegatee : $str")
    }
}

class MyDelegator {
    val delegatee: MyDelegatee = MyDelegatee()

    fun print(str: String) {
        delegatee.print(str)
    }
}

fun main() {
    val obj: MyDelegator = MyDelegator()
    obj.print("Hello Kim")
}