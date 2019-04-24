package chap08

// Set의 커스텀 클래스와 데이터 클래스의 중복 처리 비교
data class MyDataClass(val someNumericValue: Int, val someStringValue: String)
class MyCustomClass(val someNumericValue: Int, val someStringValue: String) {
    override fun toString(): String = "MyCustomClass(someNumericValue=$someNumericValue, someStringValue=$someStringValue"

    override fun hashCode(): Int = someStringValue.hashCode() + someNumericValue.hashCode()

    override fun equals(other: Any?): Boolean {
        return other is MyCustomClass && other.someNumericValue == someNumericValue &&
                other.someStringValue == someStringValue
    }
}

fun main() {
    val dataClassSet = setOf(
        MyDataClass(1, "1st"),
        MyDataClass(2, "2nd"),
        MyDataClass(3, "3rd"),
        MyDataClass(4, "4th"),
        MyDataClass(2, "2nd"),
        MyDataClass(3, "3rd"),
        MyDataClass(5, "5th"),
        MyDataClass(2, "2")
    )
    println("dataClassSet 의 아이템을 하나씩 출력")
    for (item in dataClassSet)
        println(item)

    val customClassSet = setOf(
        MyCustomClass(1, "1st"),
        MyCustomClass(2, "2nd"),
        MyCustomClass(3, "3rd"),
        MyCustomClass(4, "4th"),
        MyCustomClass(2, "2nd"),
        MyCustomClass(3, "3rd"),
        MyCustomClass(5, "5th"),
        MyCustomClass(2, "2")
    )

    println("customClassSet 의 아이템을 하나씩 출력")
    for (item in customClassSet)
        println(item)
}