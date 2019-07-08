package ch08_generic

import java.util.*

// 배열 선언 및 접근 연습
fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)

    println("arr : ${Arrays.toString(arr)}")             // 배열의 내용을 문자열로 변환
    println("size : ${arr.size}")
    println("sum(): ${arr.sum()}")

    println(arr.get(2))
    println(arr[2])

    arr.set(2, 7)
    arr[0] = 8
    println("size : ${arr.size} arr[0]: ${arr[0]}, arr[2] : ${arr[2]}")

    for (i in 0 until arr.size) {
        println("arr[$i] = ${arr[i]}")
    }
}