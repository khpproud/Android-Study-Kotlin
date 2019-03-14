package nineteen_one_two

// by에 의한 델리게이트 패턴
interface Print {
    fun print(str: String)
}

class MyDelegatee: Print {
    override fun print(str: String) {
        println("i am delegatee : $str")
    }
}

class MyDelegator(obj: MyDelegatee): Print by obj

fun main() {
    val obj: MyDelegatee = MyDelegatee()
    MyDelegator(obj).print("Hello Kim")
}