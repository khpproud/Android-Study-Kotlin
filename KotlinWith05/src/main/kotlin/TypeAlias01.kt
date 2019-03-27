package five.type

// typealias 형식 별칭 사용 예
data class User(val name: String, val surname: String)
typealias Users = List<User>

typealias Weight = Double
typealias Length = Int

val users: Users = listOf(User("Bart", "Simpson"), User("Lisa", "Simpson"))

val weight: Weight = 52.0
val length: Length = 23

// 긴 제네릭에도 사용 가능
typealias Dictionary<V> = Map<String, V>
typealias Array2D<T> = Array<Array<T>>

// 함수 형식에 이름을 지정하는데도 사용 가능
typealias Action<T> = (T) -> Unit
typealias CustomHandler = (Int, String, Any) -> Unit

// 함수 형식의 매개변수 이름을 함께 사용할 수 있음
typealias OnElementClicked = (position: Int, view: Any) -> Unit

fun main() {

}
