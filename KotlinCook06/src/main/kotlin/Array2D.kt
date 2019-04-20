package chap06

// 2차원 배열을 만드는 방법
fun main() {
    // 10 * 10 배열을 만들고 0으로 초기화
    val arr2d = Array<IntArray>(10) { IntArray(10) { 0 } }

    // 2개의 1차원 배열을 인수로 전달, arrayOf의 생성자를 호출하여 2차원 배열을 만듬
    val even = intArrayOf(2, 4, 6)
    val odd = intArrayOf(1, 3, 5)
    val total = arrayOf(odd, even)
    total.forEach {
        it.forEach { it1 ->
            print("$it1 ")
        }
        println()
    }

    // 사용자 정의 함수 사용
    val arr2d2 = array2d(10, 10) { 0 }
}

// 사용자 정의 함수를 만듬
inline fun <reified inside> array2d(sizeOuter: Int, sizeInner: Int,
                                    noinline innerInit: (Int) -> inside): Array<Array<inside>>
            = Array(sizeOuter) { Array<inside>(sizeInner, innerInit) }