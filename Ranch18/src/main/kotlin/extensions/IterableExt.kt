package extensions

// 랜덤 확장 함수 정의
fun <T> Iterable<T>.random(): T = this.shuffled().first()