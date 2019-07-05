package ch06.property

import kotlin.properties.Delegates

// vetoable() 함수 사용하여 최댓값 구하기
fun main() {
    var max: Int by Delegates.vetoable(0) {               // 초기값은 0
         _, old, new ->
        println("old : $old, new : $new")
        new > old                                                   // 새 값이 기존 값 보다 큰 경우에만 변경
    }

    println(max)
    max = 10
    println(max)

    max = 5                                                         // 여기서는 새값이 기존값 보다 작으므로 변경되지 않음
    println(max)
}