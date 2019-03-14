package fifteen_one

class Address {
    val city: String? = "Seoul"
}

class User {
    val address: Address? = Address()
}

fun main() {
    // ?.를 이용한 null 체크
    var data: String? = "Kim"
    var length: Int? = data?.length
    println(length)

    data = null
    length = data?.length
    println(length)

    val user: User? = User()
    println(user?.address?.city)

    // let()을 이용한 구문 실행
    val array = arrayOf("Hello", null, "World", "!!!")

    array.forEach {
        it?.let {
            println("$it ... ${it.length}")
        }
    }

    // 엘비스 연산자 ?:
    var data2: String? = "Park"

    var length2: Int = data2?.length ?: -1
    println("length : $length2")

    data2 = null
    length2 = data2?.length ?: -1
    println("length : $length2")
    data2 ?: println("data2 is null!!!")

    // 예외 발생 연산자 !!
    data2 = "Kim"
    println(data2!!.length)

    // NPE 발생
    data2 = null
    //println(data2!!.length)

    // 안전한 캐스팅 as?
    val strData: String = "Lee"
    val intData: Int? = strData as? Int
    println(intData)
}