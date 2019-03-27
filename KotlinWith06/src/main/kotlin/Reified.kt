package six.one

// reified 형식 매개변수
fun <T> typeCheckE(s: Any) {
    // 형식 삭제 때문에 T의 인스턴스를 확인할 수 없다
    //if (s is T) { }
}

interface View
class ProfileView: View
class HomeView: View
inline fun <reified T> typeCheck(s: Any) {
    if (s is T) {
        println("The same types!!!")
    } else {
        println("Different types!!!")
    }
}

fun main() {
    typeCheck<ProfileView>(ProfileView())
    typeCheck<HomeView>(ProfileView())
    typeCheck<View>(ProfileView())
}