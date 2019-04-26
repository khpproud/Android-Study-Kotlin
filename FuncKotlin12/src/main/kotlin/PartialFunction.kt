package chap12

import arrow.core.PartialFunction
import arrow.core.invokeOrElse
import arrow.core.orElse


// 부분 함수 : 파라미터 타입의 가능한 모든 값에 대해 정의되지 않은 함수
fun main() {
    // 부분 함수 null을 반환할 수 없다
    val upper: (String?) -> String = { s: String? -> s!!.toUpperCase() }

    // NPE 발생
    //listOf("one", "two", null, "four").map(upper).forEach(::println)

    // 애로우는 type (T) -> R의 partial 함수로 명시적 타입의 PartialFunction<T, R>을 제공
    val partialUpper: PartialFunction<String?, String> =
        PartialFunction(definedAt = { s: String? -> s != null }, ifDefined = upper)

    // IAE 발생 null은 이 함수에서 지원되지 않음
    //listOf("one", "two", null, "four").map(partialUpper).forEach(::println)

    // 예외 발생을 피하려면 부분 함수를 전체 함수로 변환해야 함
    // 정의되지 않은 경우 기본값을 반환 -> invokeOrElse
    listOf("one", "two", null, "four").map {
        s -> partialUpper.invokeOrElse(s, "Null")
    }.forEach(::println)

    // 두번째 옵션은 orElse 함수를 사용해 여러 부분 함수를 전체 함수로 만드는 것
    val upperForNull: PartialFunction<String?, String> = PartialFunction( { s -> s == null }) { "NULL" }

    val totalUpper: PartialFunction<String?, String> = partialUpper orElse upperForNull

    listOf("one", "two", null, "four").map(totalUpper).forEach(::println)

    // iisDefinedAt(T) 함수를 통해 내부 술어를 재사용할 수 있다
    val fizz = PartialFunction( {n: Int -> n % 3 == 0 }) { "FIZZ" }
    val buzz = PartialFunction( {n: Int -> n % 5 == 0 }) { "BUZZ" }
    val fizzBuzz = PartialFunction(
        { n: Int -> fizz.isDefinedAt(n) && buzz.isDefinedAt(n) }) { "FIZZBUZZ" }
    val pass = PartialFunction( { true } ) { n: Int -> n.toString() }

    // orElse 체인에서 사용될 때 선언 순서가 우선시되며, 값에 대해 조건을 작성 앞의 조건에 해당되면 뒤의 체인은 무시됨
    (1.. 50).map(fizzBuzz orElse buzz orElse fizz orElse pass).forEach(::println)
}