package chap10

// 어플리커티브(Applicative)의 정의 와 사용 예
// 래퍼 내의 람다를 같은 종류의 래퍼내 파라미터로 실행
// 모나드는 어플리커티브를 확장하고, 어플리커티브는 펑터를 확장
// 상위 타입(higher-type)을 지원하지 않으므로 유효한 코드는 아님
//interface Functor<C<_>> {
//    fun <A, B> map(ca: C<A>, transform: (A) -> B): C<B>
//}
//
//interface Applicative<C<_>>: Functor<C> {
//    fun <A> pure(a: A): C<A>                                // 순수(pure) 함수
//    fun <A, B> ap(ca: C<A>, fab: C<(A) -> B>): C<B>         // ap(apply)함수
//}
//
//interface Monad<C<_>>: Applicative<C> {
//    fun <A, B> flatMap(ca: C<A>, fm: (A) -> C<B>): C<B>
//}

// List<T>에 대한 ap 확장 함수를 정의
fun <T, R> List<T>.ap(fab: List<(T) -> R>): List<R> = fab.flatMap { f -> map(f) }

// Option 클래스에 pure와 ap를 추가 - Option.pure는 Option.Some 생성자에 대한 단순한 별칭
fun <T> Option.Companion.pure(t: T): Option<T> = Option.Some(t)

// Option.ap
fun <T, R> Option<T>.ap(fab: Option<(T) -> R>): Option<R> = fab.flatMap { f -> map(f) }

// 약간의 트릭으로 읽기 쉽게 만듬
infix fun <T, R> Option<(T) -> R>.`(*)`(o: Option<T>): Option<R> = flatMap { f: (T) -> R -> o.map(f) }

// 함수 행동을 어플리커티브처럼 만들수 있다
// 우선 pure 함수 추가
object Function1 {
    fun <A, B> pure(b: B) = { _: A -> b}                    // 항등 함수와 같음
}

// flatMap, ap를 함수 (A) -> B 에 추가
fun <A, B, C> ((A) -> B).map3(transform: (B) -> C): (A) -> C = { t -> transform(this(t)) }

fun <A, B, C> ((A) -> B).flatMap(fm: (B) -> (A) -> C): (A) -> C = { t -> fm(this(t))(t) }

fun <A, B, C> ((A) -> B).ap(fab: (A) -> (B) -> C): (A) -> C = fab.flatMap { f -> map(f) }

// 모나드절의 예제
fun main() {
    val numbers = listOf(1, 2, 3)           // 숫자 리스트
    val functions = listOf<(Int) -> Int>( { i -> i * 2 }, { i -> i + 3 } )      // 함수 리스트
    val result = numbers.flatMap { number ->
        functions.map { f -> f(number) }
    }.joinToString()
    println(result)

    // ap 함수로 재작성
    val result2 = numbers.ap(functions).joinToString()
    println(result2)                // 순서가 바뀜

    // 아래와 동일 - 순서 관계에 유의
    val result3 = functions.flatMap { numbers.map(it) }.joinToString()
    println(result3)

    // 두 Option<Int>를 합산
    val maybeFive = Option.Some(5)
    val maybeTwo = Option.Some(2)

    println(maybeFive.flatMap { f ->
        maybeTwo.map { t ->
            f + t
        }
    })

    // 어플리커티브를 사용
    println(maybeTwo.ap(maybeFive.map { f: Int -> { t: Int -> f + t } }))

    // 중위 확장 함수 `(*)`를 사용해 연산을 왼쪽부터 오른쪽으로 읽을 수 있게 함
    println(Option.pure { f: Int -> { t: Int -> f + t }} `(*)` maybeFive `(*)` maybeTwo)


    // Function1.pure(t: T)는 함수의 T를 래핑하고 사용하는 파라미터와 관계 없이 그것을 반환 => 항등함수와 같다
    val f: (String) -> Int = Function1.pure(0)
    println(f("Hello"))
    println(f("World"))
    println(f("!!!"))

    // 함수의 ap 예제
    val add3AndMultiplyBy2: (Int) -> Int = { i: Int -> i + 3 }.ap { { j: Int -> j * 2 } }
    println(add3AndMultiplyBy2(0))
    println(add3AndMultiplyBy2(1))
    println(add3AndMultiplyBy2(2))

    // 함수의 ap는 원래의 파라미터에 접근할 수 있다
    val add3AndMultiplyBy2WithIndex: (Int) -> Pair<Int, Int> = { i: Int -> i + 3 }.ap {
        original -> { j : Int -> original to (j * 2) }
    }

    println(add3AndMultiplyBy2WithIndex(0))
    println(add3AndMultiplyBy2WithIndex(1))
    println(add3AndMultiplyBy2WithIndex(2))
}