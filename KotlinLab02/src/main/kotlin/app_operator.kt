import java.util.*

fun main() {
    val array = arrayOf(10, 20, 30)
    val list = Arrays.asList(1, 2, array[0], array[1], array[2], 100, 200)
    list.forEach(::println)

    // 전개 연산자
    val list2 = Arrays.asList(1, 2, *array, 100, 200)
    list2.forEach(::println)

    val array2 = arrayOf("hello", "world")
    some(*array2)

    // 전개 연산자를 List에 이용
    val list3 = listOf("a", "b", "c")
    some(*list3.toTypedArray())

    // 논리 연산자의 동작 원리
    fun trueFun(): Boolean {
        println("call trueFun()...")
        return true
    }
    fun falseFun(): Boolean {
        println("call falseFun()...")
        return false
    }

    println("trueFun() && trueFun() : ")
    trueFun() && trueFun()
    println("falseFun() && trueFun() : ")
    falseFun() && trueFun()
    println("falseFun() || trueFun() : ")
    falseFun() || trueFun()
    println("trueFun() || trueFun() : ")
    trueFun() || trueFun()

    // 일치 연산자(일반 객채의 경우)
    class User
    val user1 = User()
    val user2 = User()
    val user3 = user1
    println("user1 == user2 is ${user1 == user2}")
    println("user1 === user2 is ${user1 === user2}")
    println("user1 == user3 is ${user1 == user3}")
    println("user1 === user3 is ${user1 === user3}")

    // 일치 연산자, 일반 객체의 null 허용의 경우
    val user4 = User()
    val user5: User? = user4
    println("user4 == user5 is ${user4 == user5}")
    println("user4 === user5 is ${user4 === user5}")

    // 일치 연산자, 기초 데이터 타입 객체(Integer)
    val int1 = Integer(10)
    val int2 = Integer(10)
    val int3 = int1
    println("int1 == int2 is ${int1 == int2}")
    println("int1 === int2 is ${int1 === int2}")
    println("int1 == int3 is ${int1 == int3}")
    println("int1 === int3 is ${int1 === int3}")

    // 일치 연산자, 기초 데이터 타입 객체(Int)
    val data1: Int = 10
    val data2: Int = 10
    println("data1 == data2 is ${data1 == data2}")
    println("data1 === data2 is ${data1 === data2}")

    // 일치 연산자, 기초 데이터 타입 객체, null 허용
    val data3 = 1000
    val data4 = 1000
    val data5: Int? = 1000
    val data6: Int? = 1000
    println("data3 == data4 is ${data3 == data4}")
    println("data3 === data4 is ${data3 === data4}")
    println("data5 == data6 is ${data5 == data6}")
    println("data5 === data6 is ${data5 === data6}")

    // Int 값에 따른 상이한 동작
    val boxed1: Int? = 127
    val boxed2: Int? = 127
    val boxed3: Int? = 128
    val boxed4: Int? = 128
    println("boxed1 === boxed2 is ${boxed1 === boxed2}") // true
    println("boxed3 === boxed4 is ${boxed3 === boxed4}") // false

    // Double 타입의 비교
    val double1: Double? = 10.0
    val double2: Double? = 10.0
    println("double1 == double2 is ${double1 == double2}")
    println("double1 === double2 is ${double1 === double2}")

    // 범위 연산자
    println("5 in 1.. 10 : ${5 in 1.. 10}")

}

fun some(vararg a: String) {
    val iterator = a.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}