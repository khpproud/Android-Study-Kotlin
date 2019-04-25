package chap10

// 펑터(Functor)에 대한 예(map 함수)
// 펑터 타입에 대한 제네릭 인터페이스 정의
// 유효하지 않은 코드(상류 타입(higher-kinded)을 지원하지 않으므로)
//interface Functor<C<_>> {
//    fun<A, B> map(ca: C<A>, transform: (A) -> B): C<B>
//}

// 타입이 함수이거나 확장 함수일 떄 맵은 펑터다

// map 함수 정의
fun <T, R> Option<T>.map(transform: (T) -> R): Option<R> = when (this) {
    Option.None -> Option.None
    is Option.Some -> Option.Some(transform(value))
}

// 확장 함수는 아주 유연해 다음과 같이 function type, (A) -> B 에 대한 map 함수를 작성
// 파라미터 함수 transform: (B) -> C 를 함수 (A) -> B 자신의 결과에 적용해 반환 타입을 B에서 C로 바꿈
fun <A, B, C> ((A) ->B).map(transform: (B) -> C): (A) -> C = {
    transform(this(it))
}

// 다음과 같은 방식으로 사용
fun main() {
    println(Option.Some("Kotlin")
        .map(String::toUpperCase))
    // Some과 None에서 다르게 동작
    println(Option.None.map(String::toUpperCase))

    val add3AndMultiplyBy2: (Int) -> Int = { i: Int -> i + 3 }.map { it * 2 }
    println(add3AndMultiplyBy2(0))
    println(add3AndMultiplyBy2(1))
    println(add3AndMultiplyBy2(2))
}