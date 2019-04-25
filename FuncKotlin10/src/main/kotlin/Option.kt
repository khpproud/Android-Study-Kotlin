package chap10

// 간단한 Option 타입 정의
sealed class Option<out T> {
    object None: Option<Nothing>() {
        override fun toString() = "None"
    }
    data class Some<out T>(val value: T): Option<T>()

    companion object
}