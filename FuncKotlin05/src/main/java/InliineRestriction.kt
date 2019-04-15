package chap05

// 인라인 제한 : 인라인 람다 함수는 어떤 방식으로든(저장, 복사 등) 조작 할 수 없다
// UserService는 (User) -> Unit 인 리스너 목록을 저장함
data class User(val name: String)
class UserService {
    val listeners = mutableListOf<(User) -> Unit>()
    val users = mutableListOf<User>()
    fun addListener(listener: (User) -> Unit) {
        listeners += listener
    }

    // addListener 를 인라인 함수로 바꾸면 컴파일 에러 발생 : 잘못된 인라인 파라미터 리스널
//    inline fun addListeners(listener: (User) -> Unit) {
//        listeners += listener
//    }
    // 람다를 인라인화 했을 때 람다를 본문으로 대체히면 맵을 저장할 수 없음

    // 다음과 같이 noinline 수정자로 대체
    inline fun addListenerNoInline(noinline listener: (User) -> Unit) {
        listeners += listener
    }

    // 인라인 람다 함수는 다른 실행 컨텍스트(로컬 오브젝트, 중첩된 람다) 내부에서 실행될 수 없다
//    inline fun transformName(transform: (name: String) -> String): List<User> {
//        val buildUser = { name: String ->
//            // 컴파일 에러: 여기서 transform 을 인라인화 할 수 없음
//            User(transform(name))
//        }
//        return users.map { user -> buildUser(user.name) }
//    }

    // crossinline 수정자 사용
    inline fun transformName(crossinline transform: (name: String) -> String): List<User> {
        val buildUser = { name: String ->
            User(transform(name))
        }
        return users.map { user -> buildUser(user.name) }
    }
}

fun main() {
    val user = User("Kim")
    val userService = UserService()

    userService.transformName(String::toLowerCase)
}