fun main() {
    // arrayOf 함수 이용
    var array = arrayOf(1, "Park", true)
    array[0] = 10
    array[2] = "world"
    println("${array[0]} .. ${array[1]} .. ${array[2]}")
    println("size : ${array.size} .. ${array.get(0)} .. ${array.get(1)} .. ${array.get(2)}")

    var arrayInt = intArrayOf(10, 20, 30)
    var arrayDouble = doubleArrayOf(10.0, 20.0, 30.0)

    // Array 클래스를 이용한 배열
    var array3 = Array(3) { i: Int -> i * 10 }
    var array4 = Array(3) { i -> i * 10}
    var array5 = IntArray(3) {i -> i * 10}
    println("${array3[0]} .. ${array3[1]} .. ${array3[2]}")

    var array6 = arrayOfNulls<Any>(3)
    array6[0] = 10
    array6[1] = "hello"
    array6[2] = true
    array6.forEach { i -> print(i) }
    println()

    var emptyArray = Array(3) {""}
    emptyArray[0] = "Hello"
    emptyArray[1] = "World"
    emptyArray[2] = "!!!"
    emptyArray.forEach { i -> print(i) }
    println()
}