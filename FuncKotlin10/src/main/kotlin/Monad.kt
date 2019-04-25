package chap10

// 모나드(monad) - flatMap 에 대한 정의 및 사용 예
// 람다를 받고 같은 타입을 반환, List<T> 가 flatMap 함수를 정의
//fun main() {
//    val result = listOf(1, 2, 3)
//        .flatMap { i -> listOf(i * 2, i + 3) }
//        .joinToString()
//    println(result)
//}

// 제네릭 모나드 정의
// 유효하지 않은 코틀린 코드
//interface Monad<C<_>>: Functor<C> {
//    fun <A, B> flatMap(ca: C<A>, fm:(A) -> C<B>): C<B>
//}

// Option type에 대해 flatMap 함수를 작성
fun <T, R> Option<T>.flatMap(fm: (T) -> Option<R>): Option<R> = when (this) {
    Option.None -> Option.None
    is Option.Some -> fm(value)
}

// map과 flatMap은 매우 유사하기 때문에 다음과 같이 재작성도 가능
fun <T, R> Option<T>.map2(transform: (T) -> R): Option<R> = flatMap {
    t -> Option.Some(transform(t))
}

// flatMap 함수의 사용 예
fun calculateDiscount(price: Option<Double>): Option<Double> {
    return price.flatMap { p ->
        if (p > 50.0) {
            Option.Some(5.0)
        } else {
            Option.None
        }
    }
}

fun main() {
    println(calculateDiscount(Option.Some(80.0)))
    println(calculateDiscount(Option.Some(30.0)))
    println(calculateDiscount(Option.None))

    // flatMap의 중첩
    val maybeFive = Option.Some(5)
    val maybeTwo = Option.Some(2)
    // 내부 flatMap 함수에서는 두 값에 모두 접근 하고 계산 가능
    println(maybeFive.flatMap { f ->
        maybeTwo.flatMap { t ->
            Option.Some(f + t)
        }
    })

    // flatMap과 map을 결합해 더 짧게 작성
    println(maybeFive.flatMap { f ->
        maybeTwo.map { it + f }
    })

    // 따라서 첫 번째 flatMap 예제를 두 리스트(숫자 리스트와 함수 리스트)의 구성으로 다시 작성
    val numbers = listOf(1, 2, 3)
    val functions = listOf<(Int) -> Int>( {i -> i * 2 }, { i -> i + 3 })
    val result = numbers.flatMap { number ->
        functions.map { f -> f(number) }
    }.joinToString()

    println(result)
}

