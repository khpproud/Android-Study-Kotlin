package chap02.impl

import chap02.impl.FunList.Cons;
import chap02.impl.FunList.Nil;
import chap02.resursion.executionTime

// 함수형 리스트 실제 구현 예

// Nothing 타입의 Nil(Null) 과 Cons(T 와 FunList<T>, 즉 Nil 혹은 또 다른 Cons 를 가짐)
// 기술적으로 FunList 는 ADT(대수적) 타입 이다. 즉 , Nil Or Cons 둘 중에 하나로 지칭됨
// (Sealed 클래스이기 때문에, 유사하게 Enum 도 있음)
sealed class FunList<out T> {
    object Nil : FunList<Nothing>()

    data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T> ()

    // forEach 구현
    fun forEach(f: (T) -> Unit) {
        tailrec fun go(list: FunList<T>, f: (T) -> Unit) {
            when (list) {
                is Cons -> {
                    f(list.head)
                    go(list.tail, f)
                }
                is Nil -> Unit              // 아무 것도 하지 않음
            }
        }
        go(this, f)
    }

    // fold 구현
    fun <R> fold(init: R, f: (R, T) -> R): R {
        tailrec fun go(list: FunList<T>, init: R, f: (R, T) -> R): R =
                when (list) {
                    is Cons -> go(list.tail, f(init, list.head), f)
                    is Nil -> init
                }
        return go(this, init, f)
    }

    // 리스트를 역순으로 반환하는 reverse 구현 (위의 함수를 재사용)
    fun reverse(): FunList<T> = fold(Nil as FunList<T>) { acc, i -> Cons(i, acc) }

    // foldRight 구현 (위의 함수를 또 재사용)
    fun <R> foldRight(init: R, f: (R, T) -> R): R {
        return this.reverse().fold(init, f)
    }

    // 이제 map 구현
    fun <R> map(f: (T) -> R): FunList<R> {
        return foldRight(Nil as FunList<R>) { tail, head -> Cons(f(head), tail)}
    }
}

// 위의 리스트 클래스의 초기화 함수 만듬
fun intListOf(vararg numbers: Int): FunList<Int> {
    return if (numbers.isEmpty())
        Nil
    else
        // 스프레드 연산자(*) 사용하여 꼬리값의 IntArray 을 추출
        Cons(numbers.first(), intListOf(*numbers.drop(1).toTypedArray().toIntArray()))
}

fun main() {
    // 함수적이지만 읽기 쉽지 않은 리스트 인스턴스
    val numbers = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

    // 위의 초기화 함수 사용한 리스트
    val numbers2 = intListOf(1, 2, 3, 4)

    // FunList 에 구현된 forEach() 사용
    numbers.forEach { i -> println("i = $i") }

    // 구현된 fold() 사용
    val sum = numbers.fold(0) { acc, i -> acc + i}
    println(sum)

    // 최적화된 코틀린의 기능 함수와 위의 구현된 함수 실행 속도 비교
    val funList = intListOf(1, 2, 3, 4, 5)
    val list = listOf(1, 2, 3, 4, 5)

    println("funList 에서 fold 실행 : ${executionTime { funList.fold(0) {acc, i -> acc + i }}}")
    println("list 에서 fold 실행 : ${executionTime { list.fold(0) { acc, i -> acc + i }}}")

    // 구현된 map 사용
    val numbersTwice = numbers.map { it.times(2) }
    numbersTwice.forEach(::println)
}

