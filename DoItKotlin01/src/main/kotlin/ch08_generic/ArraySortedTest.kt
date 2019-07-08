package ch08_generic

import java.util.*

// 기본적인 정렬 예
fun main() {
    val arr = arrayOf(8, 4, 3, 2, 5, 9, 1)
    // 오름 차순 , 내림 차순으로 정렬된 일반 배열 반환
    val sortedNumbers = arr.sortedArray()
    println("ASC : ${Arrays.toString(sortedNumbers)}")
    val sortedNumbersDesc = arr.sortedArrayDescending()
    println("DEC : ${Arrays.toString(sortedNumbersDesc)}")

    // 원본 배열에 대한 정렬
    arr.sort(1, 3)
    println("ORI : ${Arrays.toString(arr)}")
    arr.sortDescending()
    println("ORI : ${Arrays.toString(arr)}")

    // List 로 반환
    val listSorted: List<Int> = arr.sorted()
    val listDesc: List<Int> = arr.sortedDescending()
    println("LIST : $listSorted")
    println("LIST : $listDesc")

    // sortBy 를 이용한 특정 표현식에 따른 정렬
    val items = arrayOf<String>("Dog", "Cat", "Lion", "Kangaroo", "Po")
    items.sortBy { i -> i.length }
    println(Arrays.toString(items))
}